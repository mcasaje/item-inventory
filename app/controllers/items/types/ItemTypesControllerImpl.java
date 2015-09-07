package controllers.items.types;

import models.items.fields.Field;
import models.items.fields.FieldRepository;
import models.items.tags.Tag;
import models.items.tags.TagRepository;
import models.items.types.ItemType;
import models.items.types.ItemTypeRepository;
import models.jpa.JPAUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

class ItemTypesControllerImpl implements ItemTypesController {

    private final JPAUtils jpaUtils;
    private final ItemTypeRepository itemTypeRepository;
    private TagRepository tagRepository;
    private FieldRepository fieldRepository;

    @Inject
    ItemTypesControllerImpl(JPAUtils jpaUtils, ItemTypeRepository itemTypeRepository, TagRepository tagRepository, FieldRepository fieldRepository) {
        this.jpaUtils = jpaUtils;
        this.itemTypeRepository = itemTypeRepository;
        this.tagRepository = tagRepository;
        this.fieldRepository = fieldRepository;
    }

    @Override
    public boolean checkItemTypeExists(int itemTypeId) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            ItemType itemType = itemTypeRepository.findItemType(entityManager, itemTypeId);
            entityManager.getTransaction().commit();

            return itemType != null;

        } finally {
            entityManager.close();
        }

    }

    @Override
    public ItemType createItemType(String name, String username) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            ItemType itemType = itemTypeRepository.insert(entityManager, name, username);
            entityManager.getTransaction().commit();

            return itemType;

        } finally {
            entityManager.close();
        }

    }

    @Override
    public ItemType getItemType(int itemTypeId) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            ItemType itemType = itemTypeRepository.findItemType(entityManager, itemTypeId);
            entityManager.getTransaction().commit();

            return itemType;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ItemType> getItemTypes(String username) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            List<ItemType> itemTypes = itemTypeRepository.findItemTypes(entityManager, username);
            entityManager.getTransaction().commit();

            return itemTypes;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public Field addFieldToItemType(int itemTypeId, String username, String fieldName) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            Field field = fieldRepository.insertField(entityManager, fieldName, itemTypeId, username);
            entityManager.getTransaction().commit();

            return field;

        } finally {
            entityManager.close();
        }

    }

    @Override
    public Tag addTagToItemType(int itemTypeId, String username, String tagName) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            Tag tag = tagRepository.insertTag(entityManager, tagName, itemTypeId, username);
            entityManager.getTransaction().commit();

            return tag;

        } finally {
            entityManager.close();
        }

    }
}
