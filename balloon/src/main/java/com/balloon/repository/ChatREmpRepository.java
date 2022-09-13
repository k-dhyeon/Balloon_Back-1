package com.balloon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balloon.entity.ChatroomEmployee;
import com.balloon.entity.ChatroomEmployeeId;

@Repository
public interface ChatREmpRepository extends JpaRepository<ChatroomEmployee, ChatroomEmployeeId> {

	@Query(value = "SELECT * FROM chatroom_employee " + "WHERE chatroom_id IN " + "   (SELECT chatroom_id "
			+ "      FROM chatroom " + "        WHERE head_count = 2 " + "        AND chatroom_id IN "
			+ "         (SELECT chatroom_id " + "            FROM chatroom_employee "
			+ "                WHERE emp_id = :empId " + "         ) " + "   ) "
			+ "AND emp_id != :empId ;", nativeQuery = true)
	public List<ChatroomEmployee> findAll(@Param("empId") String empId);

	//////
	public List<ChatroomEmployee> findAllByChatroomIdChatroomId(Long chatroomId);

	@Query(value = "SELECT * " + "FROM chatroom_employee " + "WHERE emp_id in (:emp_Ids)"
			+ "AND chatroom_id IN (SELECT chatroom_id " + "               FROM chatroom_employee "
			+ "               WHERE emp_id=\"Y0000001\");", nativeQuery = true)
	public List<ChatroomEmployee> findChatroomEmployeeByempId(@Param("emp_Ids") List<String> empIds);

	@Query(value = "SELECT * " + "FROM chatroom_employee " + "WHERE emp_id in (:emp_Ids)"
			+ "AND chatroom_id IN (SELECT chatroom_id " + "               FROM chatroom_employee "
			+ "               WHERE emp_id=\"Y0000002\");", nativeQuery = true)
	public List<ChatroomEmployee> findChatroomEmployeeByempId2(@Param("emp_Ids") List<String> empIds);

}