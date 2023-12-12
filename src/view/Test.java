package view;

import java.sql.SQLException;

import controller.Add;
import controller.Delete;
import controller.History;
import controller.Purchase;
import controller.Search;
import controller.Show;
import controller.Update;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Show a1=new Show();
		a1.a();
        a1.s();
        a1.u();
        a1.d();
        a1.p();
        a1.H();
        a1.sh();
	}

}
