#include <iostream>
#include <vector>
#include <unordered_map>
#include <tuple>
#include <string>

using namespace std;

// Define a data record with a tuple (e.g., Store, Product, Year, Sales)
using Record = tuple<string, string, int, double>;

// Utility function to create hash for tuple keys
template <typename T>
struct TupleHash {
    size_t operator()(const T& tuple) const {
        return hash<string>()(get<0>(tuple)) ^
               hash<string>()(get<1>(tuple)) ^
               hash<int>()(get<2>(tuple));
    }
};

// Class to represent the Data Cube
class DataCube {
public:
    // Function to insert a record into the data cube
    void insertRecord(const Record& record) {
        records.push_back(record);
    }

    // Function to compute the data cube with aggregation
    void computeCube() {
        cout << "Computing data cube..." << endl;
        // Store all possible aggregated results in a map
        unordered_map<tuple<string, string, int>, double, TupleHash<tuple<string, string, int>>> cubeMap;

        // Loop through all records and aggregate for each dimension combination
        for (const auto& record : records) {
            auto store = get<0>(record);
            auto product = get<1>(record);
            auto year = get<2>(record);
            auto sales = get<3>(record);

            // Aggregate on all combinations of dimensions
            aggregate(cubeMap, make_tuple(store, product, year), sales);
            aggregate(cubeMap, make_tuple(store, product, -1), sales); // Aggregating by store & product
            aggregate(cubeMap, make_tuple(store, "*", year), sales);   // Aggregating by store & year
            aggregate(cubeMap, make_tuple("*", product, year), sales); // Aggregating by product & year
            aggregate(cubeMap, make_tuple(store, "*", -1), sales);     // Aggregating by store only
            aggregate(cubeMap, make_tuple("*", product, -1), sales);   // Aggregating by product only
            aggregate(cubeMap, make_tuple("*", "*", year), sales);     // Aggregating by year only
            aggregate(cubeMap, make_tuple("*", "*", -1), sales);       // Total sales aggregate
        }

        // Print out all cube results
        printCube(cubeMap);
    }

private:
    vector<Record> records;

    // Helper function to aggregate sales for a particular key
    void aggregate(unordered_map<tuple<string, string, int>, double, TupleHash<tuple<string, string, int>>>& cubeMap,
                   const tuple<string, string, int>& key,
                   double sales) {
        cubeMap[key] += sales;
    }

    // Helper function to print the results of the data cube
    void printCube(const unordered_map<tuple<string, string, int>, double, TupleHash<tuple<string, string, int>>>& cubeMap) {
        for (const auto& entry : cubeMap) {
            auto key = entry.first;
            auto sales = entry.second;

            string store = (get<0>(key) == "*") ? "ALL" : get<0>(key);
            string product = (get<1>(key) == "*") ? "ALL" : get<1>(key);
            string year = (get<2>(key) == -1) ? "ALL" : to_string(get<2>(key));

            cout << "Store: " << store << ", Product: " << product << ", Year: " << year << ", Sales: " << sales << endl;
        }
    }
};

int main() {
    DataCube dataCube;

    // Insert sample records (Store, Product, Year, Sales)
    dataCube.insertRecord(make_tuple("StoreA", "ProductX", 2022, 5000.0));
    dataCube.insertRecord(make_tuple("StoreA", "ProductX", 2023, 7000.0));
    dataCube.insertRecord(make_tuple("StoreA", "ProductY", 2022, 3000.0));
    dataCube.insertRecord(make_tuple("StoreB", "ProductX", 2022, 2000.0));
    dataCube.insertRecord(make_tuple("StoreB", "ProductY", 2023, 8000.0));

    // Compute and print the data cube
    dataCube.computeCube();

    return 0;
}
