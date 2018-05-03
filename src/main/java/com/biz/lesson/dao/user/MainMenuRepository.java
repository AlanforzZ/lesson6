package com.biz.lesson.dao.user;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.user.MainMenu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MainMenuRepository extends CommonJpaRepository<MainMenu, Long>, MainMenuDao {

    List<MainMenu> findByOrderByCodeAscNameAsc();

    @Modifying
    @Query(value = "update adm_menuitem set mainMenu_id=:mainMenu_id where id=:id", nativeQuery = true)
    void updateItemParentId(@Param("mainMenu_id") Long mainMenu_id, @Param("id") Long id);
}
