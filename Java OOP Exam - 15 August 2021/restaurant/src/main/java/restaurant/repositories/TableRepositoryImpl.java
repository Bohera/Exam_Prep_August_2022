package restaurant.repositories;

import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl implements TableRepository<Table> {

    private Collection<Table> tables;

    public TableRepositoryImpl() {
        tables = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableCollection(tables);
    }

    @Override
    public void add(Table table) {
        tables.add(table);
    }

    @Override
    public Table byNumber(int tableNumber) {
        Table neededTable = null;
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                neededTable = table;
            }
        }
        return neededTable;
    }
}
