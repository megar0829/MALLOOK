package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.entity.Products;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductsCustomRepositoryImpl implements ProductsCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate ;

    @Override
    public Slice<Products> findByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory) {
        Query query = new Query().with(pageable).addCriteria(Criteria.where("id").lt(cursor));
        if (mainCategory != null) {
            query.addCriteria(Criteria.where("mainCategory").is(mainCategory));
        }
        if (subCategory != null) {
            query.addCriteria(Criteria.where("subCategory").is(subCategory));
        }
        System.out.println(query);

        List<Products> productsList = mongoTemplate.find(query, Products.class);

        boolean hasNext =  mongoTemplate.count(query, Products.class) >( pageable.getPageNumber() * pageable.getPageSize() + productsList.size() );
        return new SliceImpl<>(productsList, pageable,hasNext);
    }

//
//    @Override
//    public Slice<Products> findByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory) {
//        QProducts qProducts = QProducts.products;
//        System.out.println(qProducts);
//        List<Products> products = from(qProducts)
//                .where(
//                        allEq(mainCategory, subCategory),
//                        qProducts.id.lt(cursor)
//                        )
//                .orderBy(qProducts.id.desc())
//                .limit(pageable.getPageSize() + 1)
//                .fetch();
//        System.out.println("$$$$$$$$$$$$$$$$$$$$$" + products);
//        return new SliceImpl<>(products.stream()
//                .limit(pageable.getPageSize())
//                .collect(Collectors.toList()),
//                pageable,
//                products.size() > pageable.getPageSize());
//
//    }
//
//    private BooleanExpression allEq(String mainCategory,
//                                    String subCategory) {
////        System.out.println("$$$$$$$$$$$$$$$$$$$" + mainCategoryEq(mainCategory).and(subCategoryEq(subCategory)));
//        return mainCategoryEq(mainCategory).and(subCategoryEq(subCategory));
//    }
//
//    private BooleanExpression mainCategoryEq(String mainCategory) {
//        QProducts qProducts = QProducts.products;
////        System.out.println(qProducts.main_category.eq(mainCategory));
//        return mainCategory != null ? qProducts.main_category.eq(mainCategory) : Expressions.asBoolean(true).isTrue();
//    }
//
//    private BooleanExpression subCategoryEq(String subCategory) {
//        QProducts qProducts = QProducts.products;
//        return subCategory != null ? qProducts.sub_category.eq(subCategory) : Expressions.asBoolean(true).isTrue();
//    }
}
