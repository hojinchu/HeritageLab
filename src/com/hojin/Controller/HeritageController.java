package com.hojin.Controller;

import com.hojin.com.hojin.model.HeritageDAO;
import com.hojin.com.hojin.model.HeritageVO;

import java.util.ArrayList;


/**
 * Created by hojin on 15. 9. 23.
 */
public class HeritageController {
    //컨트롤러는 해당 기능을 서비스 혹은 DAO에게 요청한다.
    HeritageDAO heritageDAO=new HeritageDAO();

    public ArrayList<HeritageVO> receiveData(int begin,int end){
        return heritageDAO.receiveData(begin,end);
    }

    public HeritageVO receiveDetailData(String heritageId){
        return heritageDAO.receiveDetailData(heritageId);
    }

}
