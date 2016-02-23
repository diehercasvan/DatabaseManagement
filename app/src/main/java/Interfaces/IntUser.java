package Interfaces;


import java.sql.SQLException;
import java.util.ArrayList;
import DTO.DtoUser;

/**
 * Created by DIEGO CASALLAS on 22/02/2016.
 */
public interface IntUser {
    int insertUser(DtoUser dtoUser) throws SQLException;
    boolean searchUserMail(DtoUser dtoUser) throws SQLException;
    ArrayList<DtoUser> consultUser(DtoUser dtoUser, int iTypeConsult) throws SQLException;
    int deleteUser(DtoUser dtoUser) throws SQLException;
    ArrayList<DtoUser> updateUser(DtoUser dtoUser) throws SQLException;
}
