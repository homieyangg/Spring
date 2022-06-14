<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.html"%>
<meta charset="UTF-8">
<title>Lung-Hi Peace訂單管理系統</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
	<!-- _navbar -->
	<%@include file="/includes/navbar.html"%>
	<!-- partial -->
	<div class="container-fluid page-body-wrapper">
		<!-- partial:partials/_settings-panel.html -->
		<div id="right-sidebar" class="settings-panel">
			<i class="settings-close ti-close"></i>
			<ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
				<li class="nav-item"><a class="nav-link active" id="todo-tab"
					data-bs-toggle="tab" href="#todo-section" role="tab"
					aria-controls="todo-section" aria-expanded="true">TO DO LIST</a></li>
				<li class="nav-item"><a class="nav-link" id="chats-tab"
					data-bs-toggle="tab" href="#chats-section" role="tab"
					aria-controls="chats-section">CHATS</a></li>
			</ul>
		</div>
		<!-- partial -->
		<!-- partial:partials/_sidebar.html -->
		<%@include file="/includes/sidebar.html"%>
		<!-- partial -->
		<!-- 顯示區域從這開始 -->
		<div class="main-panel">        
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-6 grid-margin stretch-card container">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title" >New Order</h4>
                  <form id="form" class="forms-sample" action="" method="post">
                    <div class="form-group">
                      <label for="exampleInputUsername1">商品名稱</label>
                      <input type="text" class="form-control" name="od_name" id="od_name"  value="<c:out value='${order.od_name}'/>" id="od_name">
                    </div>
                    <div class="form-group">
                    <label> <span>商品目錄</span> 
						<select name="od_catalogue" id="od_catalogue" class="js-example-basic-single w-100 select2-hidden-accessible">
						<option value="貓">貓</option>
						<option value="狗">狗</option>
						<option value="鳥">鳥</option>
						</select>
					</label>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputUsername1">訂單內容</label>
                      <input type="text" class="form-control" name="od_content" id="od_content"  value="<c:out value='${order.od_content}'/>" id="od_content">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputUsername1">訂單規格</label>
                      <input type="text" class="form-control" name="od_amount" id="od_amount"  value="<c:out value='${order.od_amount}'/>" id="od_amount">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputUsername1">訂單數量</label>
                      <input type="text" class="form-control" name="od_number" id="od_number"  value="<c:out value='${order.od_number}'/>" id="od_number" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputUsername1">訂單金額</label>
                      <input type="text" class="form-control" name="od_money" id="od_money"  value="<c:out value='${order.od_money}'/>" id="od_money" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')">
                    </div>
                    <button type="button" class="btn btn-warning me-2" onclick="add();"><i class="bi bi-send"></i>送出</button>
                    <button type="button" class="btn btn-light" onclick="cancel();">Cancel</button>
                  </form>
                		</div>
              		</div>
            	</div>
            </div>
		</div>
	</div>
	</div>
	<!-- 主畫面結束 -->
	<!-- container-scroller -->
	
	<!-- Js匯入 -->
	<!-- plugins:js -->
	<script src="vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<script src="vendors/chart.js/Chart.min.js"></script>
	<script src="vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script src="vendors/progressbar.js/progressbar.min.js"></script>

	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script src="js/off-canvas.js"></script>
	<script src="js/hoverable-collapse.js"></script>
	<script src="js/template.js"></script>
	<script src="js/settings.js"></script>
	<script src="js/todolist.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script src="js/jquery.cookie.js" type="text/javascript"></script>
	<script src="js/dashboard.js"></script>
	<script src="js/Chart.roundedBarCharts.js"></script>
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<!-- dataTable -->
	<script src="//cdn.datatables.net/1.12.0/js/jquery.dataTables.min.js"></script>
	<!-- SweetAlert2 -->
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<!-- 	dataTable.js -->
	<script type="text/javascript">
	function cancel(){
		window.location.href = "../order/"
	}
	function add(){
		var form = document.getElementById("form").value;
		var od_name = document.getElementById("od_name").value;
		var od_catalogue = document.getElementById("od_catalogue").value;
		var od_content = document.getElementById("od_content").value;
		var od_amount = document.getElementById("od_amount").value;
		var od_number = document.getElementById("od_number").value;
		var od_money = document.getElementById("od_money").value;
		if (od_name == null || od_name == ''){
			Swal.fire({
				  icon: 'error',
				  title: '請輸入商品名稱！！',
				})
			return;
		}
		if (od_content == null || od_content == ''){
			Swal.fire({
				  icon: 'error',
				  title: '請輸入訂單內容！！',
				})
			return;
		}
		if (od_amount == null || od_amount == ''){
			Swal.fire({
				  icon: 'error',
				  title: '請輸入訂單規格！！',
				})
			return;
		}
		if (od_number == null || od_number == ''){
			Swal.fire({
				  icon: 'error',
				  title: '請輸入訂單數量！！',
				})
			return;
		}
		if (od_money == null || od_money == ''){
			Swal.fire({
				  icon: 'error',
				  title: '請輸入訂單金額！！',
				})
			return;
		}
		if(${order == null}){
			Swal.fire({
				  title: 'Are you sure?',
				  text: "您將新增一筆訂單",
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: '新增!'
				}).then((result) => {
				  if (result.isConfirmed) {
				    Swal.fire(
				      '新增成功!',
				      '您已成功新增一筆訂單',
				      'success'
				    )
			document.getElementById("form").submit();
				  }
				})
		}
		if(${order != null}){
			Swal.fire({
				  title: 'Are you sure?',
				  text: "您將修改一筆訂單",
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: '修改!'
				}).then((result) => {
				  if (result.isConfirmed) {
				    Swal.fire(
				      '修改成功!',
				      '您已成功修改一筆訂單',
				      'success'
				    )
			document.getElementById("form").submit();
				  }
				})
		}
	}
	</script>
</body>
</html>