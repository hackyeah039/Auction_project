<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>

<style>
  
  #page-wrapper {
    padding-left: 250px;
  }
  
  #sidebar-wrapper {
/*
    position: fixed;
*/
    width: 250px;
    height: 100%;
    margin-left: -250px;
    background: #000;
    overflow-x: hidden;
    overflow-y: auto;
  }
  
  #page-content-wrapper {
    width: 100%;
    padding: 20px;
  }
  /* 사이드바 스타일 */
  
  .sidebar-nav {
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
  }
  
  .sidebar-nav li {
    text-indent: 1.5em;
    line-height: 2.8em;
  }
  
  .sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: #999;
  }
  
  .sidebar-nav li a:hover {
    color: #fff;
    background: rgba(255, 255, 255, 0.2);
  }
  
  .sidebar-nav > .sidebar-brand {
    font-size: 1.3em;
    line-height: 3em;
  }

</style>

  <!-- 사이드바 -->
  <div id="sidebar-wrapper">
    <ul class="sidebar-nav">
      <li class="sidebar-brand">
        <a href="#">구매관리</a>
      </li>
      <li><a href="${cp }/mypage/buyerBidding.do">입찰중</a></li>
      <li><a href="${cp }/mypage/buyerTranCompleted.do">구매종료</a></li>
      <li><a href="${cp }/mypage/buyerTransact.do">거래진행중</a></li>
      <li class="sidebar-brand">
        <a href="#">판매관리</a>
      </li>
      <li><a href="${cp }/mypage/sellerBidding.do">입찰중</a></li>
      <li><a href="${cp }/mypage/sellerTranCompleted.do">구매종료</a></li>
      <li><a href="${cp }/mypage/sellerTransact.do">거래진행중</a></li>
    </ul>
  </div>
  
  
  <!-- 

  <div id="page-content-wrapper">
    <div class="container-fluid">
      <h1>간단한 사이드바</h1>
      <p>메뉴가 많아서 한 페이지를 넘으면 세로 스크롤바 생김</p>
    </div>
  </div>
  
  
   -->
