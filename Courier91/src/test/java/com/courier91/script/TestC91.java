package com.courier91.script;

import java.io.IOException;

import org.testng.annotations.Test;

import com.courier91.apiWorkspace.OrderDenyUserConfirmation;
import com.courier91.apiWorkspace.PostAPI;
import com.courier91.apiWorkspace.PostApiForReturnProcess;
import com.courier91.genric.BaseLib;
import com.courier91.pageObject.HamburgerMenuPage;
import com.courier91.pageObject.LotOrdersPage;
import com.courier91.pageObject.NewOrdersPage;
import com.courier91.pageObject.Order_to_be_picked_from_customer;
import com.courier91.pageObject.OrdersToBeDeliveredPage;
import com.courier91.pageObject.Re_dispatch_ordersPage;
import com.courier91.pageObject.ReturnOrderPage;
import com.courier91.pageObject.Return_orders_tracking_Page;
import com.courier91.pageObject.ScanOderPage;

public class TestC91 extends BaseLib {
	@Test(priority=1)  //,invocationCount = 5
	public void test_DeliveryProcess() throws IOException {
		ScanOderPage sop = new ScanOderPage(driver)	;
		sop.clickOnUnscannedTAb();
		sop.FunctionalityOfUnscanToScan();
		sop.clickOnGlobalScanner();
		sop.scanOrderId();
		sop.clickOnScannedTab();
		sop.clickOnToBeDeliveredTAb();
		OrdersToBeDeliveredPage odp= new OrdersToBeDeliveredPage(driver);
		odp.deliverykare();
		odp.sendOtp();	
	}
	@Test(priority=2)//invocationCount = 10
	public void test_DenyProcess() throws IOException {
		ScanOderPage sop = new ScanOderPage(driver)	;
		sop.clickOnUnscannedTAb();
		sop.FunctionalityOfUnscanToScan();
		sop.clickOnGlobalScanner();
		sop.scanOrderId();
		sop.clickOnScannedTab();
		sop.clickOnToBeDeliveredTAb();	
		OrdersToBeDeliveredPage odp= new OrdersToBeDeliveredPage(driver);
		odp.returnkare();
		ReturnOrderPage rop = new ReturnOrderPage(driver);
		rop.clickOnTobeReturnedTAb();
		rop.orderIdInUnderProcessing();
		PostAPI.callApiForREadyToReturn();
		rop.clickOnReadyToReturnTab();
		Return_orders_tracking_Page rot = new Return_orders_tracking_Page(driver);
		rot.clickcOnOrderTRackingTab();
		rot.checkLotId();
	}
	@Test(priority=3) //invocationCount = 10
	public void test_ReturnProcess() throws IOException {
		ScanOderPage sop = new ScanOderPage(driver)	;
		sop.clickOnUnscannedTAb();
		sop.FunctionalityOfUnscanToScan();
		sop.clickOnGlobalScanner();
		sop.scanOrderId();
		sop.clickOnScannedTab();
		sop.clickOnToBeDeliveredTAb();
		OrdersToBeDeliveredPage odp= new OrdersToBeDeliveredPage(driver);
		odp.deliverykare();
		odp.sendOtp();
		PostApiForReturnProcess.ReturnOrderApi();
		Order_to_be_picked_from_customer otp = new Order_to_be_picked_from_customer(driver);													
		otp.callApiForqueueRunning();
		otp.clickcOnToBePickedTab();
		otp.orderPickKareFunctionality();
		ReturnOrderPage rop = new ReturnOrderPage(driver);
		rop.clickOnTobeReturnedTAb();
		rop.clickOnReadyToReturnTab();
		Return_orders_tracking_Page rot = new Return_orders_tracking_Page(driver);
		rot.clickcOnOrderTRackingTab();
		rot.checkLotId();	
	}
	@Test(priority=4)
	public void test_HambergerMenuFunctionality() {
		HamburgerMenuPage hmp = new HamburgerMenuPage(driver);
		hmp.clickOnHamburgerMenu();
		hmp.test_BarcodeScannerFunctionality();

	}
	@Test(priority=5)
	public void testRedispatchFunctionality() throws IOException {
		ScanOderPage sop = new ScanOderPage(driver)	;
		sop.clickOnUnscannedTAb();
		sop.FunctionalityOfUnscanToScan();
		sop.clickOnGlobalScanner();
		sop.scanOrderId();
		sop.clickOnScannedTab();
		sop.clickOnToBeDeliveredTAb();	
		OrdersToBeDeliveredPage odp= new OrdersToBeDeliveredPage(driver);
		odp.returnkare();
		OrderDenyUserConfirmation.OrderDenyUserConfirmationApi();


	}
	@Test(priority=7)
	public void test_Global_Scanner_Functionality_IncomingTab() throws IOException {
		NewOrdersPage nop = new NewOrdersPage(driver);
		nop.clickOntheIncomingTab();
		nop.selectOrderIdForIncomingTab();
		nop.clickOnBackButton();
		nop.validateIncomingOrderFRomGlobalScanner();
	}
@Test(priority=8)
public void test_globalScanner_Functionality_UnscannedTab() throws IOException {
		ScanOderPage sop = new ScanOderPage(driver)	;
		sop.clickOnUnscannedTAb();
		sop.selectOrderIdForUnscannedTab();
		sop.clickOnBackButton();
		sop.validateUnscannedTabOrderFRomGlobalScanner();		
	}
	@Test(priority=6)
	public void test_ScanTab_Functionality_Thorough_GlobalScanner() throws IOException {
		ScanOderPage sop = new ScanOderPage(driver)	;
		sop.clickOnUnscannedTAb();	
		sop.selectOrderIdForScannedTab();
		sop.clickOnBackButton();
		sop.validateScannedTabOrderFRomGlobalScanner();
	}
	@Test(priority=9)
	public void test_ToBeDeliveredFunctionality_Throught_globalScanner() throws IOException {
		ScanOderPage sop = new ScanOderPage(driver)	;	
		sop.clickOnToBeDeliveredTAb();
		OrdersToBeDeliveredPage otd= new OrdersToBeDeliveredPage(driver);
		otd.selectOrderIdForToBeDeliveredTab();
		sop.clickOnBackButton();
		otd.validateToBeDeliveredTabOrderIdFRomGlobalScanner();	
	}
	@Test(priority=10)
	public void test_ToBePickedFunctionality_Throught_globalScanner() throws IOException {
		Order_to_be_picked_from_customer otp = new Order_to_be_picked_from_customer(driver);
		otp.clickcOnToBePickedTab();
		otp.selectOrderIdForToBePickedTab();
		otp.clickOnBackButton();
		otp.validateToBePickedTabOrderIdFRomGlobalScanner();	
	}
	@Test(priority=12)
	public void test_UnderprocessingFunctionality_Throught_globalScanner() throws IOException {
		ReturnOrderPage rop = new ReturnOrderPage(driver);
		rop.clickOnTobeReturnedTAb();
		rop.selectOrderIdForUnderProcessingTab();
		rop.clickOnBackButton();
		rop.validateUnderProcessingIdFRomGlobalScanner();
	}
	@Test(priority=11)
	public void test_ReadyToReturnFunctionality_Throught_globalScanner() throws IOException {
		ReturnOrderPage rop = new ReturnOrderPage(driver);
		rop.clickOnTobeReturnedTAb();
		rop.selectOrderIdForReadyToReturnTab();
		rop.clickOnBackButton();
		rop.validateReadyToREturnIdFRomGlobalScanner();
	}
	@Test(priority=13)
	public void test_ReDispatchOrder_Throught_globalScanner() throws IOException {
		Re_dispatch_ordersPage rdo= new Re_dispatch_ordersPage(driver);
		rdo.clickOntheREdispatchTab();
		rdo.selectOrderIdForRedispatchTab();
		rdo.clickOnBackButton();
		rdo.validateReDispatchOrderIdFRomGlobalScanner();
	}
	@Test(priority=14)
	public void test_OrderTrackingOrderId_Throught_globalScanner() throws IOException {
		Return_orders_tracking_Page rot = new Return_orders_tracking_Page(driver);
		rot.clickcOnOrderTRackingTab();
		rot.clickOnViewOrders();
		LotOrdersPage lop = new LotOrdersPage(driver);
		lop.selectOrderIdForOrderTackingTab();
		rot.clickOnBackButton();
		rot.clickOnBackButton();
		lop.validateOrderTrackingOrderIdFRomGlobalScanner();
		
	}
	
}

