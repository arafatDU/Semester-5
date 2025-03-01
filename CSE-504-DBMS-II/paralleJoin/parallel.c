#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <time.h>

#define NUM_PARTITIONS 4  // We assume a 4-core system for this example
#define SIZE1 1000       // Example size for Table1
#define SIZE2 1000       // Example size for Table2

typedef struct {
    int TID;
    int Sales;
} Table1;

typedef struct {
    int TID;
    int Quantity;
} Table2;

typedef struct {
    int TID;
    int Sales;
    int Quantity;
} Result;

// Function to initialize large datasets for Table1 and Table2
void initialize_data(Table1 **table1, int size1, Table2 **table2, int size2) {
    *table1 = (Table1 *)malloc(size1 * sizeof(Table1));
    *table2 = (Table2 *)malloc(size2 * sizeof(Table2));

    // Seed random number generator for reproducibility
    srand(time(NULL));

    // Generate unique TIDs for Table1 and Table2
    for (int i = 0; i < size1; i++) {
        (*table1)[i].TID = i + 1;  // Unique TIDs from 1 to size1
        (*table1)[i].Sales = rand() % 100000;  // Random sales amount
    }

    for (int i = 0; i < size2; i++) {
        (*table2)[i].TID = (i + 1) * 2;  // Unique TIDs as multiples of 2 to ensure some overlap
        (*table2)[i].Quantity = rand() % 100;  // Random quantity
    }
}

// Function to perform parallel join with each range of Table2 processed by a separate core
Result *parallel_join(Table1 *table1, int size1, Table2 *table2, int size2, int *result_size) {
    int partition_size = size2 / NUM_PARTITIONS;
    int partition_start, partition_end;
    
    // Allocate memory for results with an upper bound (assume worst case)
    Result *results = (Result *)malloc(size1 * size2 * sizeof(Result));
    *result_size = 0;

    // Parallel region with each core processing one range of Table2
    #pragma omp parallel num_threads(NUM_PARTITIONS) shared(results, result_size)
    {
        int part = omp_get_thread_num();
        partition_start = part * partition_size;
        partition_end = (part == NUM_PARTITIONS - 1) ? size2 : (part + 1) * partition_size;

        // Print which core is handling which range
        printf("Core %d processing Table2 range [%d, %d)\n", part, partition_start, partition_end);

        int local_result_size = 0;
        Result *local_results = (Result *)malloc(size1 * partition_size * sizeof(Result));

        for (int i = 0; i < size1; i++) {
            for (int j = partition_start; j < partition_end; j++) {
                if (table1[i].TID == table2[j].TID) {
                    local_results[local_result_size].TID = table1[i].TID;
                    local_results[local_result_size].Sales = table1[i].Sales;
                    local_results[local_result_size].Quantity = table2[j].Quantity;
                    local_result_size++;
                }
            }
        }

        // Aggregate local results into the shared results array
        #pragma omp critical
        {
            for (int k = 0; k < local_result_size; k++) {
                results[*result_size] = local_results[k];
                (*result_size)++;
            }
        }
        free(local_results);
    }
    return results;
}

// Main function
int main() {
    Table1 *table1;
    Table2 *table2;
    int size1 = SIZE1, size2 = SIZE2;
    initialize_data(&table1, size1, &table2, size2);

    int result_size;
    Result *results = parallel_join(table1, size1, table2, size2, &result_size);

    // Print a portion of the result for verification
    printf("TID\tSales\tQuantity\n");
    for (int i = 0; i < result_size && i < 20; i++) {  // Print only first 20 results for demonstration
        printf("%d\t%d\t%d\n", results[i].TID, results[i].Sales, results[i].Quantity);
    }

    // Free allocated memory
    free(table1);
    free(table2);
    free(results);

    return 0;
}
