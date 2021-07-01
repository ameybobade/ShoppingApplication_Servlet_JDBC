package com.model;

public class Registration {

		private String name;
		private String uname;
		private int phone;
		private String email;
		private String password;
		
		public Registration(String name, String uname, int phone, String email, String password) {
			super();
			this.name = name;
			this.uname = uname;
			this.phone = phone;
			this.email = email;
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUname() {
			return uname;
		}
		public void setUname(String uname) {
			this.uname = uname;
		}
		public int getPhone() {
			return phone;
		}
		public void setPhone(int phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
}
