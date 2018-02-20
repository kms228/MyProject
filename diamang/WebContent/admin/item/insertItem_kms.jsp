<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="SearchSelect">
		<table border="1">
			<colgroup>
				<col style="width:25%" span="3">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">대분류</th>
					<th scope="col">중분류</th>
					<th scope="col">소분류</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<div class="list">
							<ul>
							<!-- li{list-style:none} -->
								
								<li><input type="hidden" name="ring" value="ring"><a href="javascript:selectRing();">(대분류)반지 ></a></li>
								<li><a href="">(대분류)목걸이 ></a></li>
								<li><a href="">(대분류)귀걸이 ></a></li>
								<li><a href="">(대분류)팔찌 ></a></li>
							</ul>
						</div>
					</td>
					<td>
						<div class="list">
							<ul id="listOf">
								
							</ul>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
