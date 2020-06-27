
var viewDetail = (data) => {
	
	return  `
	<table class="table table-bordered " border="1">
						  <thead>
						    <tr>
						      <th scope="col">LOẠI</th>
						      <th scope="col">CHỈ SỐ CŨ</th>
						      <th scope="col">CHỈ SỐ MỚI</th>
						      <th scope="col">SỐ LƯỢNG</th>
						      <th scope="col">ĐƠN GIÁ <i>(vnd)</i></th>
						      <th scope="col">THUẾ <i>(10%)</i></th>
						      <th scope="col">THÀNH TIỀN <i>(vnd)</i></th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr>
						      <th scope="row">Quản lý</th>
						      <td></td>
						      <td></td>		
						      <td></td>
						      <td>150000</td>
						      <td></td>
						      <td>150000</td>
						    </tr>
						    <tr>
						      <th scope="row">Rác</th>
						      <td></td>
						      <td></td>					  
						      <td></td>
						      <td>100000</td>
						      <td></td>
						      <td>100000</td>
						    </tr>
						    <tr>
						      <th scope="row">Xe đạp</th>
						      <td ></td>						    
						      <td></td>
						      <td>1<i> chiếc</i></td>
						      <td>80000</td>
						      <td></td>
						      <td>80000</td>	      	
						    </tr>
						     <tr>
						      <th scope="row">Xe máy</th>
						      <td ></td>
						      <td></td>						    
						      <td>2<i> chiếc</i></td>
						      <td>200000</td>
						      <td></td>
						      <td>400000</td>	      
						    </tr>
						     <tr>
						      <th scope="row">Xe ô tô</th>
						      <td ></td>
						      <td></td>					   
						      <td>1<i> chiếc</i></td>
						      <td>500000</td>
						      <td></td>
						      <td>500000</td>	      
						    </tr>
						     <tr>
						      <th scope="row">Nước</th>
						      <td>100<i> m3</i></td>
						      <td>130<i> m3</i></td>						     
						      <td>30<i> m3</i></td>
						      <td>90000</td>
						      <td></td>
						      <td>270000</td>	      
						    </tr>
						    <tr>
						      <th scope="row">Điện</th>
						      <td>100<i> kWh</i></td>
						      <td>229<i> kWh</i></td>						     
						      <td>129<i> kWh</i></td>
						      <td></td>
						      <td></td>
						      <td></td>	      
						    </tr>
						    
						     <!-------------------------- Định mức 1 --------------------------- -->
						    <tr>
						      <td rowspan="6" style="vertical-align : middle;text-align:center;">
						      Đơn giá điện bậc thang <br/>
						      <i>(Đã bao gồm 10% thuế GTGT)</i>
						       </td> 
						      <td></td>
						      <td></td>  
						      <td>50<i> kWh</i></td>
						      <td>2030</td>						     
						      <td>203</td>
						      <td>101519</td>   
						    </tr>
						    <!-------------------------- Định mức 2 --------------------------- -->
						     <tr>
						      
						      <td></td>
						      <td></td>  
						      <td>50<i> kWh</i></td>
						      <td>2098</td>						     
						      <td>210</td>
						      <td>104907</td>   
						    </tr>
						    <!-------------------------- Định mức 3 --------------------------- -->
						     <tr>
						     
						      <td></td>
						      <td></td>  
						      <td>29<i> kWh</i></td>
						      <td>2437</td>						     
						      <td>244</td>
						      <td>101519</td>   
						    </tr>
						    <!-------------------------- Định mức 4 --------------------------- -->
						     <tr>
						     
						      <td></td>
						      <td></td>  
						      <td>0</td>
						      <td>3069</td>						     
						      <td>0</td>
						      <td>0</td>   
						    </tr>
						    <!-------------------------- Định mức 5 --------------------------- -->
						     <tr>
						      
						      <td></td>
						      <td></td>  
						      <td>0</td>
						      <td>3456</td>						     
						      <td>0</td>
						      <td>0</td>   
						    </tr>
						    <!-------------------------- Định mức 6 --------------------------- -->
						     <tr>		
						     	      
						      <td></td>
						      <td></td>  
						      <td>0</td>
						      <td>3567</td>						     
						      <td>0</td>
						      <td>0</td>   
						    </tr>
						    <tr>
						      <th scope="row"><h6><strong>Tổng cộng</strong></h6></th>
						      <td colspan= "5" style="vertical-align : middle;text-align:center;">Lấy tròn</td>				   
						      <td><h6><strong>805000</strong></h6></td>   
						    </tr>
						  </tbody>
					</table>
	`
}