package com.example.ohmycost;

import java.util.ArrayList;

public interface list {

    //เอาชนิดมาเก็บใน list1
    public abstract void setType(chooseExpense type);
    //เอาเงินมาเก็บใน list2
    public abstract void setExpense(chooseExpense expense);
    //รวม list1,2 ไว้ใน list เพื่อใช้เป็น value ของ dict sec1 (ที่มี ันที่ป็นคีย์)
    public abstract ArrayList combineList();
}
