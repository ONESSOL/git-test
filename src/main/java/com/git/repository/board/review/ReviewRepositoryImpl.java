package com.git.repository.board.review;

import com.git.domain.board.review.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.git.domain.board.review.QReview.review;

public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Review> findByTitleAndContents(String search, Pageable pageable) {

        List<Review> results = queryFactory
                .select(review)
                .from(review)
                .where(firstTitleContains(search).or(contentsContains(search)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(review.count())
                .from(review)
                .where(titleContains(search), contentsContains(search));

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);

    }

    private BooleanExpression firstTitleContains(String title) {
        if(title == null) {
            title = "";
        }
        return review.title.contains(title);
    }

    private BooleanExpression titleContains(String title) {
        return StringUtils.hasText(title) ? review.title.contains(title) : null;
    }

    private BooleanExpression contentsContains(String contents) {
        return StringUtils.hasText(contents) ? review.contents.contains(contents) : null;
    }


}
