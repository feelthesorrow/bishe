function getTotal() {
    $.ajax({
        url: "http://localhost:8000/v1/cart/total",
        dataType:'json',
    }).then(function(data) {
        
        $('.total').append(data.total);
    });
}

$(document).ready(function(){
    $.ajax({
        url:"http://localhost:8000/v1/cart",
        dataType:"json",
        success: function(data){
            var allshops = data.shops;
            allshops.forEach(function(shop) {

                $('#container').append('<div class="shop"><p class="shop-name"><form><input type="radio" name="select-shop" value="select-all"></form>'
                + shop.shopName
                + '</p>'        
                );

                var groups = shop.groups;
                groups.forEach(function(group){
                    $('#container').append(
                        '<div class="activity"><p class="activity-name">'
                        + group.groupName
                        + '</p><div class="activity-items">'
                    );

                    var items = group.items;
                    items.forEach(function(item){
                        $('#container').append(
                            '<p class="item">'
                            +'<form>'
                            +        '<input type="radio" name="select-item" value="select-item">'
                            +'</form>'
                            +'<img src="'
                            + item.picUrl
                            + '" alt="loading" width="304" height="304">'
                            +'<p class="title">'
                            + item.title
                            + '</p><p class="detail">尺寸：'
                            + item.size
                            + '<br>颜色：'
                            + item.color
                            + '</p><form class="qty">'
                            +       '<input type="submit" value="-">'
                            +       '<input type="text" name="qty" value="'
                            + item.qty
                            + '"> <input type="submit" value="+"> </form><p class="subtotal"></p>'
                            + '<form class="delete"> <input type="submit" name="delect-item" value="删除"></form></p>'
                        );

                    });

                    $('#container').append(' </div> </div>');

                });

                $('#container').append('</div>');

            });

            $('#container').append('<div class="total"> </div>');
            getTotal();
            
        }

    });

});
