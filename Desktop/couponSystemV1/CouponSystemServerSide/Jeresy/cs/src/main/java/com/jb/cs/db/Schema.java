package com.jb.cs.db;

public final class Schema {

	//D- data B-base
	
	/* Table names. */
	private static final String TABLE_NAME_COUPON = "Coupon";
	private static final String TABLE_NAME_CUSTOMER = "Customer";
	private static final String TABLE_NAME_COMPANY = "Company";
	private static final String TABLE_NAME_CUSTOMER_COUPON = "Customer_Coupon";
	private static final String TABLE_NAME_COMPANY_COUPON = "Company_Coupon";
	/* Common column names. */
	private static final String COL_ID = "id";
	private static final String COL_COUPON_ID = "couponId";
	private static final String COL_NAME = "name";
	private static final String COL_PASSWORD = "password";
	/* Coupon columns. */
	private static final String COL_TITLE = "title";
	private static final String COL_START_DATE = "startDate";
	private static final String COL_END_DATE = "endDate";
	private static final String COL_AMOUNT = "amount";
	private static final String COL_CATEGORY = "category";
	private static final String COL_MESSAGE = "message";
	private static final String COL_PRICE = "price";
	private static final String COL_IMAGE_URL = "imageUrl";
	/* Company columns. */
	private static final String COL_EMAIL = "email";
	private static final String COL_COMPANY_ID = "companyId";
	/* Customer columns. */
	private static final String COL_CUSTOMER_ID = "customerId";
	
	
//	D . M . L
//	D- data M- manipulation L- language
//	 
//	                                                  C . R . U . D 
//	                                 C- create
//	                                       R- read
//	                                           U- update
//	                                                D- delete
	
	  /*Coupon tables*/
	
	//C- create   
	/* SQL Query that create the company table with all columns */ 
	private static final String CREATE_TABLE_COUPON = "create table if not exists " + TABLE_NAME_COUPON + "(" + COL_ID
			+ " integer primary key auto_increment, " + COL_TITLE + " varchar(50), " + COL_START_DATE + " date, "
			+ COL_END_DATE + " date, " + COL_AMOUNT + " integer, " + COL_CATEGORY + " integer, " + COL_MESSAGE
			+ " varchar(250), " + COL_PRICE + " double, " + COL_IMAGE_URL + " varchar(250)) ;";
	
	//R- read
	/* SQL Query that help to get and insert to coupon table, arguments and define how many arguments
	 *  we can deliver*/
	public static String getInsertCoupon() {
		return "insert into " + TABLE_NAME_COUPON + "(" + COL_TITLE + "," + COL_START_DATE + "," + COL_END_DATE + ","
				+ COL_AMOUNT + "," + COL_CATEGORY + "," + COL_MESSAGE + "," + COL_PRICE + "," + COL_IMAGE_URL
				+ ") values(?,?,?,?,?,?,?,?) ";
	}
	
	//U- update
	/* SQL Query that help to set coupon details*/
	private static final String GET_UPDATE_COUPON_BY_ID = " update " + TABLE_NAME_COUPON + " set " + COL_TITLE 
			+ " = ? , " + COL_START_DATE + " = ? , " + COL_END_DATE + " = ? , " + COL_AMOUNT + " = ? , " 
			+ COL_CATEGORY + " = ? , " + COL_MESSAGE + " = ? , " + COL_PRICE + " = ? , " + COL_IMAGE_URL 
			+ " = ?  where " + COL_ID + " = ?  ";
	
	//D- delete
	 /* SQL Query that get coupon ID locate and remove the coupon from coupon tables*/ 
	private static final String GET_DELETE_COUPON = " delete from " + TABLE_NAME_COUPON + " where " + COL_ID 
			+ "  = ?  ";



    /*Company tables*/
	
	//C- create
	/* SQL Query that create the company table with all columns */ 
	private static final String CREATE_TABLE_COMPANY = "create table if not exists " + TABLE_NAME_COMPANY + "("
    + COL_ID+ " integer primary key auto_increment," + COL_NAME + " varchar(50)," + COL_PASSWORD 
    + " varchar(50), "+ COL_EMAIL + " varchar(50));";

	/* SQL Query that create the company coupon table with all columns */ 
	private static final String CREATE_TABLE_COMPANY_COUPON = "create table if not exists " + TABLE_NAME_COMPANY_COUPON
			+ "(" + COL_COMPANY_ID + " integer, " + COL_COUPON_ID + " integer , foreign key (" + COL_COMPANY_ID
			+ ") references " + TABLE_NAME_COMPANY + "(" + COL_ID + ") on delete cascade, foreign key (" + COL_COUPON_ID
			+ ") references " + TABLE_NAME_COUPON + "(" + COL_ID + ") on delete cascade, primary key(" + COL_COMPANY_ID
			+ "," + COL_COUPON_ID + " )) ; ";
	
	//R- read
	/* SQL Query that help to get and insert to company table, arguments and define how many arguments
	 *  we can deliver*/
	public static final String getInsertCompany() {
		return "insert into " + TABLE_NAME_COMPANY + " ( " + COL_NAME + "," + COL_PASSWORD + "," + COL_EMAIL
				+ " ) values (?,?,?);";
	}

	/* SQL Query that help to get and insert to company coupon table, arguments from two tables
	 * coupon and company the columns coupon ID and company ID will combine and 
	 * fill  a raw*/
	public static final String getInsertCompanyCoupon() {
		return "insert into " + TABLE_NAME_COMPANY_COUPON + "(" + COL_COMPANY_ID + ", " 
	         + COL_COUPON_ID + " ) values (?,?) ";
	}
	
	
	//U- update
	/* SQL Query that help to set company details name, passwords and email*/
	private static final String GET_UPDATE_COMPANY = " update " 
	        + TABLE_NAME_COMPANY + " set " + COL_NAME + " = ? , " + COL_PASSWORD
	        + "  = ? , " + COL_EMAIL + " = ?  where " + COL_ID + " = ? ";
	
    //D- delete
	 /* SQL Query that get company ID locate and remove the company from company tables*/ 
	private static final String GET_DELETE_COMPANY = "delete from " + TABLE_NAME_COMPANY 
			+ " where " + COL_ID + " =? ";
	
	
	
	/*Customers tables*/
	
	//C- create
	/* SQL Query that create the customer table with all columns */ 
	private static final String CREATE_TABLE_CUSTOMER = "create table if not exists " + TABLE_NAME_CUSTOMER + "("
			+ COL_ID + " integer primary key auto_increment, " + COL_NAME + " varchar(50), " + COL_PASSWORD
			+ " varchar(50)) ";
	
	/* SQL Query that create the customer coupon table with all columns */ 
	private static final String CREATE_TABLE_CUSTOMER_COUPON = "create table if not exists " + TABLE_NAME_CUSTOMER_COUPON
			+ "(" + COL_CUSTOMER_ID + " integer, " + COL_COUPON_ID + " integer, foreign key (" + COL_CUSTOMER_ID
			 + ") references " + TABLE_NAME_CUSTOMER + "(" + COL_ID + ") on delete cascade, foreign key (" + COL_COUPON_ID
			 + ") references " + TABLE_NAME_COUPON + "(" + COL_ID + ") on delete cascade, primary key (" + COL_CUSTOMER_ID 
			 + "," + COL_COUPON_ID + " )) ; ";
	
	
	//R- read
	/* SQL Query that help to get and insert to customer table, arguments and define how many arguments
	 *  we can deliver*/
	public static final String getInsertCustomer() {
		return "insert into " + TABLE_NAME_CUSTOMER + " ( " + COL_NAME + "," + COL_PASSWORD 
				  + " ) values (?,?);";
	}

	/* SQL Query that help to get and insert to customer coupon table, arguments from two tables
	 * coupon and customer the columns coupon ID and customer ID will combine and 
	 * fill  a raw*/
	public static final String getInsertCustomerCoupon() {
		return "insert into " + TABLE_NAME_CUSTOMER_COUPON + "(" + COL_CUSTOMER_ID + ", "
              + COL_COUPON_ID + " ) values (?,?);";
	}
	
	
	//U- update
	/* SQL Query that help to set customer details name and passwords*/
	private static final String GET_UPDATE_CUSTOMER = "update " 
	        + TABLE_NAME_CUSTOMER + " set "  + COL_NAME + " = ? , "  + COL_PASSWORD 
	        + " = ? where " + COL_ID + "  = ?";
	
	
	//D- delete
	 /* SQL Query that get customer ID locate and remove the customer from customer tables*/ 
	private static final String GET_DELETE_CUSTOMER = " delete from " 
	        + TABLE_NAME_CUSTOMER + " where " + COL_ID + " = ?  ";
	
	
	
	/*Coupon getters*/
	
	public static String getCreateTableCoupon() {

		return CREATE_TABLE_COUPON;
	}
	
	public static String getGetUpdateCouponById() {
		return GET_UPDATE_COUPON_BY_ID;
	}
	
	public static String getGetDeleteCoupon() {
		return GET_DELETE_COUPON;
	}
	
	/*Company getters*/
	
	public static String getCreateTableCompany() {
		return CREATE_TABLE_COMPANY;
	
	}

	public static String getCreateTableCompanyCoupon() {
		return CREATE_TABLE_COMPANY_COUPON;

	
	}
	
	public static String getGetUpdateCompanyById() {
		return GET_UPDATE_COMPANY;
	}
	
	public static String getGetDeleteColCompany() {
		return GET_DELETE_COMPANY;
	}
	
	/*Customer getters*/

	public static String getCreateTableCustomer() {

		return CREATE_TABLE_CUSTOMER;
	}

	public static String getCreateTableCustomerCoupon() {

		return CREATE_TABLE_CUSTOMER_COUPON;
	}

	public static String getGetUpdateCustomerById() {
		return GET_UPDATE_CUSTOMER;
	}
		
		public static String getGetDeleteCustomerById() {
			return GET_DELETE_CUSTOMER;
		}

		
		/*Inner join tables company coupon*/ 
	    /*create new tables from two exist tables (combine) coupon , company.
	     *  with two parameters and they the new columns coupon ID and company ID*/
		
		private static final String GET_COMPANY_COUPONS_INNER_JOIN_BY_ID = " select * from " + TABLE_NAME_COUPON 
	    		+ " t1 inner join "+ TABLE_NAME_COMPANY_COUPON + " t2 on t1. " + COL_ID + " = t2. " 
	    		+ COL_COUPON_ID + " where t2. " + COL_COMPANY_ID + " = ? ";
		
		public static String getGetCompanyCouponsInnerJoinById() {
			return GET_COMPANY_COUPONS_INNER_JOIN_BY_ID;
		}

	
	    /*Inner join tables customer coupon*/ 
	    /*create new tables from two exist tables coupon , customer.
	     *  with two parameters and they the new columns coupon ID and customer ID*/
	
		private static final 		String GET_CUSTOMER_COUPONS_INNER_JOIN_BY_ID 
        = " select * from " + TABLE_NAME_COUPON + " t1 inner join " 
        + TABLE_NAME_CUSTOMER_COUPON + " t2 on t1. " + COL_ID + " = t2." 
     		   + COL_COUPON_ID + " where t2. " + COL_CUSTOMER_ID + " = ? ";

        public static String getGetCustomerCouponsInnerJoinById() {
	         return GET_CUSTOMER_COUPONS_INNER_JOIN_BY_ID;
     }

		
   /*Manipulation on coupon tables*/
        
    	 /* SQL Query that find all coupons in coupon table*/ 
    	private static final String GET_SELECT_ALL_COUPONS_ID =   " select " + COL_ID + " from " 
    	        + TABLE_NAME_COUPON ;
        
    	
    	 /* SQL Query that find in coupon tables , coupon by his coupon ID number*/ 
    	private static final String GET_COUPON_BY_ID =  " select * from " + TABLE_NAME_COUPON 
    			+ " where " + COL_ID + " = ?  ";
    	
    	
    	 /* SQL Query that find in coupon tables , coupon by his category*/ 
    	private static final String GET_SELECT_COUPON_By_Categry =" select * from " + TABLE_NAME_COUPON 
    			+ " where " + COL_CATEGORY + " = ?  ";
    	
    	  /* SQL Query that update the decrement of a coupon to table Coupon*/ 
    	private static final String GET_DECREMENT_COUPON_AMOUNT = " update " + TABLE_NAME_COUPON 
    			+ " set " + COL_AMOUNT + " = " + COL_AMOUNT + " -1  where " + COL_ID+ " = ? and " 
    			+ COL_AMOUNT + " > 0 ";
    	
	
    	/*Manipulation on company tables*/
    	
    	 /* SQL Query that find all companies in company table*/ 
  	   private static final String GET_SELECT_ALL_ID_COMPANIES = "select " + COL_ID + " from " 
  	            + TABLE_NAME_COMPANY + ";";
  	  
  	  
  	 /* SQL Query that find company by company ID in company table*/ 
    	  private static final String GET_SELECTED_COMPANY_BY_ID = " select * from " + TABLE_NAME_COMPANY 
    	    		+ "  where " + COL_ID + " = ? ";
    	  
    	
    	  /* SQL Query that find all companies by two parameters name and password*/ 
    	    private static final String GET_SELECTED_COMPANY_BY_NAME_AND_PASSWORD 
    	            = "select * from " + TABLE_NAME_COMPANY + " where " + COL_NAME + " = ? and " 
    	            + COL_PASSWORD + " = ? ";
	
	
    	    /*Manipulation on customer tables*/
    	    
    	    /* SQL Query that find all customers in company table*/ 
    		private static final String GET_SELECT_All_ID_CUSTOMERS = " select * from " 
    		        + TABLE_NAME_CUSTOMER ;
    		
    		/* SQL Query that find customer by customer ID in customer table*/ 
    		private static final String GET_SELECT_Customer = " select * from " +TABLE_NAME_CUSTOMER 
    				+ " where " + COL_ID + " = ? ";
    		
    		/* SQL Query that find all customers by two parameters name and password*/ 
    		private static final String GET_SELECTED_CUSTOMER_BY_NAME_AND_PASSWORD 
	         = " select * from " + TABLE_NAME_CUSTOMER + " where " + COL_NAME + " = ? and " 
	         + COL_PASSWORD + " = ? ";
    		
    		
    		
    		
    		
    		//coupon manipulation  getters
    		
    		public static String getGetDecrementcouponAmount() {
    			return GET_DECREMENT_COUPON_AMOUNT;
    		}

    		public static String getGetCouponById() {
    			return GET_COUPON_BY_ID;
    		}

    		public static String getGetSelectAllCouponsId() {
    			return GET_SELECT_ALL_COUPONS_ID;
    		}
    		public static String getGetSelectCouponByCategry() {
    			return GET_SELECT_COUPON_By_Categry;
    		}
    		
    		
    		//company manipulation getters
    		public static String getGetSelectedCompanyById() {
    			return GET_SELECTED_COMPANY_BY_ID;
    		}

    	    public static String getGetSelectAllIdCompanies() {
    			return GET_SELECT_ALL_ID_COMPANIES;
    		}

    	    public static String getGetSelectedCompanyByNameAndPassword() {
    			return GET_SELECTED_COMPANY_BY_NAME_AND_PASSWORD;
    		}
    	    
    	    
    	    //customer manipulation getters
    		public static String getGetSelectAllIdCustomers() {
    			return GET_SELECT_All_ID_CUSTOMERS;
    		}

    		public static String getGetSelectedCustomerByNameAndPassword() {
    			return GET_SELECTED_CUSTOMER_BY_NAME_AND_PASSWORD;
    		}

    		public static String getGetSelectCustomer() {
    			return GET_SELECT_Customer;
    		}

    	
}