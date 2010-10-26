using TestLibrary;

using System;
using System.Web;
using System.Web.UI;

namespace TestWeb
{
	public partial class Default : System.Web.UI.Page
	{
		public virtual void button1Clicked(object sender, EventArgs args)
		{
			string countStr = hidden1.Value;
			int count = Convert.ToInt32(countStr);
			
			button1.ToolTip = new OrderController().SayHello();
			button1.Text = "You clicked me " + (++count) + " times";
			
			hidden1.Value = count.ToString();
			
			new OrderController().TestDataAdapter();
		}
	}
}
