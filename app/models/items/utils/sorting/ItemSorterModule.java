package models.items.utils.sorting;

import com.google.inject.AbstractModule;

public class ItemSorterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ItemSorter.class).to(ItemIdSorterImpl.class);
    }
}
