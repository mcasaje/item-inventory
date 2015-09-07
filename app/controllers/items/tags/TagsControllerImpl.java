package controllers.items.tags;

import models.items.tags.TagRepository;
import models.jpa.JPAUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;

class TagsControllerImpl implements TagsController {

    private final JPAUtils jpaUtils;
    private final TagRepository tagRepository;

    @Inject
    TagsControllerImpl(JPAUtils jpaUtils, TagRepository tagRepository) {
        this.jpaUtils = jpaUtils;
        this.tagRepository = tagRepository;
    }

    @Override
    public void addTagToItem(int itemId, int tagId, String username) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            tagRepository.insertItemTag(entityManager, itemId, tagId, username);
            entityManager.getTransaction().commit();

        } finally {
            entityManager.close();
        }
    }
}
