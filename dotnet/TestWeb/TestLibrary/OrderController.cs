
using Npgsql;
using System;
using System.Data;
using System.Transactions;

namespace TestLibrary
{
	public class OrderController
	{
		private static string connectionString = @"
			Server=localhost;
        	Database=asptest;
        	User ID=asptest;
        	Password=asptest;";
		
		public String SayHello() {
			using (TransactionScope scope = new TransactionScope())
			{
				IDbConnection conn = new NpgsqlConnection(connectionString);
				conn.Open();
				IDbCommand cmd = conn.CreateCommand();
				string query = "select id, customerid from tcustomer";
				cmd.CommandText = query;
				
				IDataReader reader = cmd.ExecuteReader();
				string ret = null;
				while(reader.Read()) {
					ret = (string) reader["customerid"];
				}
				return ret;
			}
		}
		
		public void TestDataAdapter()
		{
			using (TransactionScope scope = new TransactionScope())
			{
				NpgsqlConnection conn = new NpgsqlConnection(connectionString);
				string query = "select * from tcustomer";
				IDataAdapter adapter = new NpgsqlDataAdapter(query, conn);
				
				DataSet ds = new DataSet("customer");
				adapter.Fill(ds);
				
				foreach (DataTable table in ds.Tables)
				{
					Console.WriteLine(table.TableName);
				}
				
				foreach (DataRow row in ds.Tables["Table"].Rows)
				{
					Console.WriteLine(row["customerid"]);
				}
			}
		}
			
	}
}
