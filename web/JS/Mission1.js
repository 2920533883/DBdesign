function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] === variable){return pair[1];}
    }
    return false;
}

let t = new Vue({
    el: "#top",
    data: {},
    methods: {
        search: function () {
            let name = document.getElementById("search").value
            window.location.href = "http://localhost:8888/SearchResult.html?name="+name;
        },
        addItem: function () {
            document.getElementById("add-frame").style.display = "block";
            document.getElementById("container").classList.add("blur");
            addFrame.requireType = -1;
        }
    }
})
let container = new Vue({
    el: "#container",
    data:{
        id:[],
        name:[],
        num:[],
        esDate:[],
        address:[],
        coach:[]
    },
    mounted(){
        axios.get("Http://localhost:8888/getAllContent").then(resource=>{
            let data = resource.data;
            for (let i = 0; i < data.length; ++i){
                this.id.push(data[i].id);
                this.name.push(data[i].name);
                this.num.push(data[i].num);
                this.esDate.push(data[i].esDate);
                this.coach.push(data[i].coach);
                this.address.push(data[i].address);
            }
        })
    },
    methods:{
        del: function (index) {
            let name = this.name[index];
            axios.get("http://localhost:8888/deleteContent?name="+name+"&id="+index).then(new function () {
                    alert("删除成功");
                    setTimeout(function () {
                        location.reload();
                    }, 500)
                }
            )

        },
        changeItem: function (index) {
            document.getElementById("add-frame").style.display = "block";
            document.getElementById("container").classList.add("blur");
            addFrame.requireType = index;
        }

    }
})
let addFrame = new Vue({
    el: "#add-frame",
    data:{
      requireType: -1
    },
    methods:{
        closeFrame: function () {
            document.getElementById("add-frame").style.display = "none";
            document.getElementById("container").classList.remove("blur");
        },
        submit: function () {
            let id = document.getElementById("id").value;
            let name = document.getElementById("name").value;
            let address = document.getElementById("address").value;
            let num = document.getElementById("num").value;
            let esDate = document.getElementById("esDate").value;
            let coach = document.getElementById("coach").value;
            axios.get("http://localhost:8888/changeContent?name="+name+
                "&index="+this.requireType+"&id="+id+"&coach="+coach+"&num="+num+"&esDate="+esDate+"&address="+address)
                .then(
                  new  function () {
                        setTimeout(function () {
                            location.reload();
                        }, 500)
                    })
        }
    }
})
let result = new Vue({
    el: "#result",
    data:{
        id: "空",
        name: "空",
        num: "空",
        esDate: "空",
        address: "空",
        coach: "空"
    },
    created: function(){
        let name = getQueryVariable("name");
        axios.get("http://localhost:8888/searchContent?name="+name).then(resource=>{
            if (resource.data !== null) {
                this.id = resource.data.id;
                this.name = resource.data.name;
                this.num = resource.data.num;
                this.address = resource.data.address;
                this.coach = resource.data.coach;
                this.esDate = resource.data.esDate;
            }
        })
    }
})