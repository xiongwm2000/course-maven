(function() {
  $(document).ready(function() {
    if (localStorage.getItem("content") !== null) {
      $("#color-settings-body-color").attr("href", localStorage.getItem("content"));
    }
    if ((localStorage.getItem("contrast") !== null) && !$("body").hasClass("contrast-background")) {
      $("body")[0].className = $("body")[0].className.replace(/(^|\s)contrast.*?(\s|$)/g, " ").replace(/\s\s+/g, " ").replace(/(^\s|\s$)/g, "");
      $("body").addClass(localStorage.getItem("contrast"));
    }
    $(".color-settings-body-color > a").hover(function() {
      $("#color-settings-body-color").attr("href", $(this).data("change-to"));
      return localStorage.setItem("content", $(this).data("change-to"));
    });
    return $(".color-settings-contrast-color > a").hover(function() {
      $('body')[0].className = $('body')[0].className.replace(/(^|\s)contrast.*?(\s|$)/g, ' ').replace(/\s\s+/g, ' ').replace(/(^\s|\s$)/g, '');
      $('body').addClass($(this).data("change-to"));
      return localStorage.setItem("contrast", $(this).data("change-to"));
    });
  });

}).call(this);

$(function(){
    var count=$(".fch").height(); 
	$(".fch1").height(count);
	
	$("#main-nav ul li a").click(function() {
		$("#main-nav ul li a").removeClass("selected");
		$(this).addClass("selected");
		});
	
	$("#main-nav a").each(function(){  
	    $this = $(this);  
	    var a=window.location.pathname;	   
	   /* alert(a+"             "+$this[0].pathname)*/
	   /* alert(a.indexOf($this[0].pathname));*/
	    if(a.indexOf($this[0].pathname) > -1){ 
	        $this.parent().addClass("left-li-background");  
	        $this.addClass("left-li-background1");  
	    }  
	});  

});

/*
用途：检查输入字符串是否为空或者全部都是空格
输入：str
返回：
如果全是空返回true,否则返回false
*/
function isNull( str ){
if ( str == "" ) return true;
var regu = "^[ ]+$";
var re = new RegExp(regu);
return re.test(str);
} 
/**
 * 检查两个字符是否相等
 * @return
 */
function isEquals(str1,str2)
{
	if(str1!=str2)
	{
		return false;
	}
	else return true;
}
/*
用途：检查输入手机号码是否正确
输入：
s：字符串
返回：
如果通过验证返回true,否则返回false
*/
function checkMobile( s )
{  
	/*var regu =/^[1][3][0-9]{9}$/;
	var re = new RegExp(regu);
	if (re.test(s)) {
	return true;
	}else{
	return false;
	}*/
	return/^(13|15|17|18)\d{9}$/i.test(s);
}
/*
用途：检查输入对象的值是否符合E-Mail格式
输入：str 输入的字符串
返回：如果通过验证返回true,否则返回false
*/
function isEmail( str ){ 
var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
if(myReg.test(str)) return true;
return false;
}
/*
用途：检查输入对象的值是否符合整数格式
输入：str 输入的字符串
返回：如果通过验证返回true,否则返回false
*/
function isInteger( str ){ 
var regu = /^[-]{0,1}[0-9]{1,}$/;
return regu.test(str);
}

function isDateYYMMDD(dateString){
	  if(dateString.trim()=="")return true;
	  //年月日时分秒正则表达式
	  var r = dateString.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
	  if(r==null){
	   return false;
	  }
	 return true;
	  
	} 

/**   
判断输入框中输入的日期格式是否为年月日时分秒 即 yyyy-mm-dd hh:mi:ss 
*/  
function isDate(dateString){
	  if(dateString.trim()=="")return true;
	  //年月日时分秒正则表达式
	  var r=dateString.match(/^(\d{1,4})\-(\d{1,2})\-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/); 
	  if(r==null){
	   //alert("请输入格式正确的日期\n\r日期格式：yyyy-mm-dd\n\r例    如：2008-08-08\n\r");
	   return false;
	  }
	  var d=new Date(r[1],r[2]-1,r[3],r[4],r[5],r[6]);     
	  var num = (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[2]&&d.getDate()==r[3]&&d.getHours()==r[4]&&d.getMinutes()==r[5]&&d.getSeconds()==r[6]);
	  if(num==0){
	   //alert("请输入格式正确的日期\n\r日期格式：yyyy-mm-dd\n\r例    如：2008-08-08\n\r");
	  }
	  return (num!=0);
	  
	} 
    
function replaceTextarea1(str){
    var reg=new RegExp("\r\n","g");
    var reg1=new RegExp("\n","g");
    str = str.replace(reg,"</iebr>");
    str = str.replace(reg1,"</br>");
    return str;
}
function replaceTextarea2(str){
    var reg=new RegExp("</iebr>","g");
    var reg1=new RegExp("</br>","g");
    str = str.replace(reg,"\r\n");
    str = str.replace(reg1,"\n");
    return str;
}