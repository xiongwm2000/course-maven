//tab切换
(function tabs(tabHd, on, tabBd,tabContainer) {
	var $inc = $(tabContainer),
	switchTo = function(index){
		$(tabHd).children().eq(index).addClass(on).siblings().removeClass(on);
		$(tabBd).children().eq(index).show().siblings().hide();
		$inc.trigger('tab-switch',index);
	};

	//初始化
	var index = 0;  
	switchTo(index);

	//evts
	var tabBdChildren = $(tabHd).children();
	tabBdChildren.on('click',function(event){
		event.preventDefault();
		event.stopPropagation();
		var index = tabBdChildren.index(this);
		switchTo(index);
	});
})(".tab-hd", "active", ".tab-bd",".tab");
		
$(function(){
	function searchOnfocus(){
		var inputSearch = $('.input-search'), val;
		inputSearch.focus(function(){
			val = inputSearch.val();
			if(val == "请输入关键词"){
				inputSearch.val('');
			}
		})
		inputSearch.blur(function(){
			val = inputSearch.val();
			if(val == ""){
				inputSearch.val('请输入关键词');
			}
		})	
	}
	searchOnfocus();//搜索框获得失去焦点
});