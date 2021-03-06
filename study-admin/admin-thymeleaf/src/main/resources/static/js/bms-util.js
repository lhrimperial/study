bms = {};

bms.requestAjaxPost = function(url, params, successFn, failFn){
    $.ajax({
        type: "POST",
        url: url,
        data: params,
        // dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(response){
            if(response.success){
                successFn(response);
            }else {
                failFn(response);
            }
        },
        error: function(){
            alert("系统异常！");
        }
    });
}

/**
 * 空字符串判断
 * @param value
 * @returns {boolean}
 */
function isEmpty(value) {
    return value == null || value == '';
}

function isNotEmpty(value) {
    return value != null && value != '';
}

/**
 * 定义Map类型
 */
function Map() {
    this.container = new Object();
}
/**
 * Map put方法
 */
Map.prototype.put = function(key, value) {
    if(this.containsKey(key)){
        delete this.container[key];
    }
    this.container[key] = value;
}
/**
 * Map get方法
 */
Map.prototype.get = function(key) {
    return this.container[key];
}
/**
 * Map key集合
 */
Map.prototype.keySet = function() {
    var keyset = new Array();
    var count = 0;
    for ( var key in this.container) {
        // 跳过object的extend函数
        if (key == 'extend') {
            continue;
        }
        keyset[count] = key;
        count++;
    }
    return keyset;
}
/**
 * Map tostring
 */
Map.prototype.size = function() {
    var count = 0;
    for ( var key in this.container) {
        // 跳过object的extend函数
        if (key == 'extend') {
            continue;
        }
        count++;
    }
    return count;
}

Map.prototype.remove = function(key) {
    delete this.container[key];
}

Map.prototype.clear = function(){
    for ( var key in this.container) {
        // 跳过object的extend函数
        if (key == 'extend') {
            continue;
        }
        delete this.container[key];
    }
}

Map.prototype.containsKey = function(key) {
    var bln = false;
    try {
        for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
            if (keys[i] == key) {
                bln = true;
            }
        }
    } catch (e) {
        bln = false;
    }
    return bln;
}

Map.prototype.toString = function() {
    var str = "";
    for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
        str = str + keys[i] + "=" + this.container[keys[i]] + ";\n";
    }
    return str;
}


