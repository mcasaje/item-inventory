package controllers.items.fields;

import models.items.itemfields.ItemField;
import models.items.itemfields.ItemFieldRepository;
import models.jpa.JPAUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;

class FieldsControllerImpl implements FieldsController {

    private final JPAUtils jpaUtils;
    private ItemFieldRepository itemFieldRepository;

    @Inject
    FieldsControllerImpl(JPAUtils jpaUtils, ItemFieldRepository itemFieldRepository) {
        this.jpaUtils = jpaUtils;
        this.itemFieldRepository = itemFieldRepository;
    }

    @Override
    public ItemField addFieldToItem(int itemId, int fieldId, String username, String fieldValue) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            ItemField itemField = itemFieldRepository.insertItemField(entityManager, itemId, fieldId, username, fieldValue);
            entityManager.getTransaction().commit();

            return itemField;


        } finally {
            entityManager.close();
        }
    }
}
