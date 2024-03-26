package io.ssafy.mallook.domain.style.dao;

import io.ssafy.mallook.domain.style.dto.response.StyleRes;
import io.ssafy.mallook.domain.style.entity.Style;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StyleRepository extends JpaRepository<Style, Long> {
    @Query("select max(s.id) from Style s")
    Long findMaxId();

    @Query("SELECT s FROM Style s ORDER BY s.totalLike DESC")
    List<Style> findTop50StylesOrderByTotalLikeDesc();

    Slice<StyleRes> findStylesByIdLessThan(Pageable pageable, Long cursor);

    @Modifying(clearAutomatically = true)
    @Query(value = """
            update Style s
            set s.status = false
            where s.id in :styleIdList and s.member.id = :memberId
            """
    )
    void deleteMyStyle(@Param("memberId") UUID memberId, @Param("styleIdList") List<Long> styleIdList);

    @Modifying
    @Query("UPDATE Script s SET s.totalLike = 0")
    void resetAllTotalLike();
}
