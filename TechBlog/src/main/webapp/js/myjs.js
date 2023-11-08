function doLike(pid, uid)
{
	console.log(pid+","+uid);
	
	const dt=
	{
		
		pid:pid,
		uid:uid,
		operation:"Like"
	}
	
	$.ajax({
		
		url:"LikedServlet",
		data:dt,
		success:function(data,textStatus,jqXHR){
			console.log(data);
			if(data.trim() == 'true')
			{
			let c = $(".like-counter").html();
			c++;
			$(".like-counter").html(c);
			}
		},
		error:function(jqXHR,TextStatus,errorThrown){
			console.log(data);
		}
	})
	
}