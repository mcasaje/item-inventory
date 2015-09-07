package models.items.utils.sorting.items;

import com.google.inject.AbstractModule;

public class ItemSorterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ItemSorter.class).to(ItemIdSorterImpl.class);
    }
}
