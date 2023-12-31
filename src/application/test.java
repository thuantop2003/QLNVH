package application;

import java.util.ArrayList;

import DAO.AccountDAO;
import DAO.WorkDAO;
import model.Account;
import model.Work;

public class test {
public static void main(String[] args) {
	ArrayList<Work> x= WorkDAO.getInstance().selectAll();
	for(int i=0;i<x.size();i++) {
		System.out.println(x.get(i).toString());
	}
}
}
