package com.git.repository.board.notice;

import com.git.domain.board.notice.Notice;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.git.domain.board.notice.QNotice.notice;

public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NoticeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Notice> findByTitleAndContents(String search, Pageable pageable) {

        List<Notice> results = queryFactory
                .select(notice)
                .from(notice)
                .where(firstTitleContains(search).or(contentsContains(search)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(notice.count())
                .from(notice)
                .where(titleContains(search), contentsContains(search));

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    private BooleanExpression firstTitleContains(String title) {
        if(title == null) {
            title = "";
        }
        return notice.title.contains(title);
    }

    private BooleanExpression titleContains(String title) {
        return StringUtils.hasText(title) ? notice.title.contains(title) : null;
    }

    private BooleanExpression contentsContains(String contents) {
        return StringUtils.hasText(contents) ? notice.contents.contains(contents) : null;
    }

}
































