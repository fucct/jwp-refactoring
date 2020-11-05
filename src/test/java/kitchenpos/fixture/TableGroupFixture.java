package kitchenpos.fixture;

import java.time.LocalDateTime;
import java.util.List;

import kitchenpos.domain.OrderTable;
import kitchenpos.domain.TableGroup;

public class TableGroupFixture {
    public static final Long ID1 = 1L;

    public static TableGroup createWithoutId(List<OrderTable> tables) {
        TableGroup tableGroup = new TableGroup();
        tableGroup.setOrderTables(tables);
        tableGroup.setCreatedDate(LocalDateTime.now());

        return tableGroup;
    }

    public static TableGroup createWithId(Long id, List<OrderTable> tables) {
        TableGroup tableGroup = new TableGroup();
        tableGroup.setId(id);
        tableGroup.setOrderTables(tables);
        tableGroup.setCreatedDate(LocalDateTime.now());

        return tableGroup;
    }
}
