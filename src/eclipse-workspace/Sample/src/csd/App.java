/*----------------------------------------------------------------------------------------------------------------------
	Anımsanacağı gibi Java'da 3 yerde atama yapılabilir. İstisna bir takım kurallar dışında atama işlemlerine ilişkin
	kurallar hepsi için geçerlidir. Bir metodun parametre değişkeni bir referans olabilir. Bu durumda metot aynı türden
	bir referans ile çağrılabilir. Bu durumda metot içerisinde argüman olarak geçilen referansa ilişkin nesneye 
	erişilebilir. Bir metodun parametre geri dönüş değeri bir referans olabilir. Bu durumda return deyimine ilişkin
	ifadenin de aynı türden bir referans olması gerekir. Aşağıdaki örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package csd;

class App {
	public static void main(String [] args)
	{
		Date d = DateUtil.createDate(11, 7, 1983);
		
		DateUtil.printDate(d);		
		DateUtil.changeDate(d, 6, 9, 2021);
		DateUtil.printDate(d);
	}
}

class DateUtil {
	public static Date createDate(int day, int month, int year)
	{
		//...
		Date date = new Date();
		
		date.day = day;
		date.month = month;
		date.year = year;
		
		return date;
	}
	
	public static void changeDate(Date date, int day, int month, int year)
	{
		//...
		date.day = day;
		date.month = month;
		date.year = year;
	}
	
	public static void printDate(Date date)
	{
		System.out.printf("%02d/%02d/%04d%n", date.day, date.month, date.year);
	}
}

class Date {
	public int day, month, year;
	//...
}



