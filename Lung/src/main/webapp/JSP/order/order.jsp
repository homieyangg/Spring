<%@page import="order.model.OrderBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.html"%>
<meta charset="UTF-8">
<title>Lung-Hi Peace訂單管理系統</title>
<link href="${pageContext.request.contextPath}/css/dataTable.css" rel="stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
<link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
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
		<%@include file="/./includes/sidebar.html"%>
		<!-- partial -->
		<!-- 顯示區域從這開始 -->
		<div class="main-panel">
			<div class="content-wrapper">
				<div class="row">
					<div class="col-lg grid-margin stretch-card">
						<div class="card material-table">
							<div class="table-header">
								<!-- Table標題 -->
								<span class="table-title">Order Table</span>
								<div class="actions">
									<!-- 新增 -->
									<a href="orderNew" class="modal-trigger waves-effect btn-flat nopadding"><i class="mdi mdi-plus"></i></a><!-- i標籤是icon --> 
									<!-- 搜尋 -->
									<a href="#" class="search-toggle waves-effect btn-flat nopadding"><i class="material-icons">search</i></a>
								</div>
							</div>
							<div class="table-responsive">
								<table class="table table-hover" id="datatable">
									<thead>
										<tr>
											<th>訂單編號</th>
											<th>商品名稱</th>
											<th>商品目錄</th>
											<th>訂單內容</th>
											<th>訂單規格</th>
											<th>訂單數量</th>
											<th>訂單金額</th>
											<th>test</th>
											<th>訂單操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="order" items="${listOrder}">
											<tr>
												<td><c:out value="${order.od_id}" /></td>
												<td><c:out value="${order.od_name}" /></td>
												<td><c:out value="${order.od_catalogue}" /></td>
												<td><c:out value="${order.od_content}" /></td>
												<td><c:out value="${order.od_amount}" /></td>
												<td><c:out value="${order.od_number}" /></td>
												<td><c:out value="${order.od_money}" /></td>
												<td><label class="badge badge-danger">未付款</label></td>
												<td>
													<button type="button" class="btn btn-sm btn-success"
														onclick="edit('${order.od_id}');" id="edit">
														<i class="bi bi-pencil-fill"></i>
													</button>
													<button type="button" class="btn btn-sm btn-danger"
														onclick="delete1('${order.od_id}');" id="delete1"
														>
														<i class="bi bi-trash-fill"></i>
													</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- content-wrapper ends -->
				<!-- partial:partials/_footer.html -->
				<%@include file="/includes/footer.html"%>
				<!-- partial -->
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- 主畫面結束 -->
	<!-- container-scroller -->
	
	<!-- Js匯入 -->
	<!-- plugins:js -->
	<%@include file="/includes/JavaScript.html"%>
	<!-- End custom js for this page-->
	
	<!-- 寫自己的Js -->
	<script>
	function new1(){
		window.location.href = "<%=path%>/order?action=new"
	}

	function edit(od_id){
		window.location.href = "./updateOrder?od_id="+od_id
	}
	
	function delete1(od_id){
		const swalWithBootstrapButtons = Swal.mixin({
			  customClass: {
			    confirmButton: 'btn btn-danger',
			    cancelButton: 'btn btn-secondary'
			  },
			  buttonsStyling: false
			})

			swalWithBootstrapButtons.fire({
			  title: '確定刪除此訂單嗎?',
			  icon: 'warning',
			  showCancelButton: true,
			  cancelButtonText: '等等我在想想',
			  confirmButtonText: '快把它刪了!',
			  reverseButtons: true
			}).then((result) => {
			  if (result.isConfirmed) {
			    swalWithBootstrapButtons.fire(
			      'Deleted!',
			      '你已經成功刪除',
			      'success'
			    )
				var timeoutID = window.setTimeout(( () =>  window.location.href = "./DeleteOrder?od_id="+od_id ), 1000);
			  } else if (
			    /* Read more about handling dismissals below */
			    result.dismiss === Swal.DismissReason.cancel
			  ) {
			    swalWithBootstrapButtons.fire(
			      '取消刪除',
			      '你成功保護了此訂單 :)',
			      'error'
			    )
			  }
			})
	}
	</script>
</body>
</html>
