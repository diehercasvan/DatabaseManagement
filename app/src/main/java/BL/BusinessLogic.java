package BL;


import java.util.ArrayList;
import DTO.DtoUser;

/**
 * Created by DIEGO CASALLAS on 22/02/2016.
 */
public class BusinessLogic {
    private ArrayList<DtoUser> arrayList;

    public int insertUserBl(DtoUser dtoUser){

        return 1;
    }
    public  ArrayList<DtoUser> consultUserBl(DtoUser dtoUser, int iTypeSearch){

        return arrayList;
    }
    public int deleteUserBl(DtoUser dtoUser){

        return 1;
    }
    public ArrayList<DtoUser> updateUserBl(DtoUser dtoUser){

        return arrayList;
    }

}
