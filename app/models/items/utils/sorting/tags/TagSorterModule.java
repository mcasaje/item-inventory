package models.items.utils.sorting.tags;

import com.google.inject.AbstractModule;

public class TagSorterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TagSorter.class).to(TagIdSorterImpl.class);
    }
}
