def atomicRecovery(log):
    undo = set()
    redo = set()
    checkpoint = set()
    transactions = {'A': 0, 'B': 0, 'C': 0, 'D': 0, 'E':0}

    for line in log:
        if 'START' in line:
            undo.add(line[7:-1])
        elif 'COMMIT' in line:
            redo.add(line[8:-1])
        elif 'CKPT(' in line:
            temp = line[line.index('(') + 1:line.index(')')].split(',')
            checkpoint.update(temp)
            checkpoint = undo.difference(checkpoint)
        else:
            parts = line.strip('<>').split()
            if len(parts) == 4:
                transaction, var, old_val, new_val = parts
                if transaction in undo:
                    transactions[var] = new_val  
                if transaction in redo:
                    transactions[var] = new_val 

    undo = undo.difference(redo)
    redo = redo.difference(checkpoint)

    print("Redo transactions:")
    for transaction in redo:
        print(transaction)
    
    print("\nUndo transactions:")
    for transaction in undo:
        print(transaction)

    for transaction in undo:
        for line in log:
            if f'<{transaction}' in line:
                parts = line.strip('<>').split()
                if len(parts) == 4:
                    _, var, old_val, new_val = parts
                    transactions[var] = old_val
                    
    print("\nFinal values:")
    for var in ['A', 'B', 'C', 'D', 'E']:
        print(f'{var} = {transactions[var]}')

def main():
    with open("log.txt") as file:
        log = [line.strip() for line in file.readlines()]
    atomicRecovery(log)

if __name__ == '__main__':
    main()
