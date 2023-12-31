package application;

import java.util.ArrayList;

import DAO.AccountDAO;
import DAO.RoomDAO;
import DAO.WorkDAO;
import model.Account;
import model.Room;
import model.Work;

public class test {
public static void main(String[] args) {
	ArrayList<Room> x= RoomDAO.getInstance().selectAll();
	for(int i=0;i<x.size();i++) {
		System.out.println(x.get(i).toString());
	}
}
}
