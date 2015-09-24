package com.hojin.view;

import com.hojin.Controller.HeritageController;
import com.hojin.com.hojin.model.HeritageVO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hojin on 15. 9. 24.
 */
public class HeritageDetailView extends JDialog{
    HeritageVO heritageVO=null;
    String heritageArray=null;
    HeritageController heritageController;

    JLabel jLabel=new JLabel("문화재 상세 화면");
    JLabel jLabelNo=new JLabel("문화재 번호");
    JLabel jLabelName=new JLabel("문화재 이름");
    JLabel jLabelArea=new JLabel("문화재 면적");
    JLabel jLabelExplain=new JLabel("문화재 상세 설명");

    public HeritageDetailView(String heritageArray) {
        this.heritageArray = heritageArray;
        this.setTitle("문화재 상세 화면");

        heritageController=new HeritageController();
        heritageVO=heritageController.receiveDetailData(heritageArray);

        setDetailDisplay();
    }

    private void setDetailDisplay(){
        String heritageId=heritageVO.getHeritageId();
        String heritageTitle=heritageVO.getHeritageTitle();
        String heritageSize=heritageVO.getHeritageSize();
        String heritageInfo=heritageVO.getHeritageInfo();

        jLabelNo.setText("문화재 번호: "+heritageId);
        jLabelName.setText("문화재 이름: "+heritageTitle);
        jLabelArea.setText("문화재 면적: "+heritageSize);
        jLabelExplain.setText("문화재 상세 설명: "+heritageInfo);

        setLayout(new GridLayout(4,0));
        add(jLabelNo);
        add(jLabelName);
        add(jLabelArea);
        add(jLabelExplain);
        setSize(600,500);
        setModal(true);
        setVisible(true);
    }
}


















