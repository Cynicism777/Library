package com.example.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.library.model.Borrow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {
    @Insert("INSERT INTO borrow (book_id, book_name, user_id, username, borrow_time) VALUES (#{bookId}, #{bookName}, #{userId}, #{username}, #{borrowTime})")
    int insertBorrowRecord(Borrow borrow);
    @Update("UPDATE borrow SET return_time = #{returnTime}, penalty = #{penalty} WHERE id = #{id}")
    int updateReturnInfo(Borrow borrow);
    @Select("SELECT * FROM borrow WHERE user_id = #{userId} AND return_time IS NULL")
    List<Borrow> getBorrowedBooksByUserId(int userId);
    @Select("SELECT * FROM borrow")
    List<Borrow> getAllBorrowRecords();
}
