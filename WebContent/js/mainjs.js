	//문서의 내용이 모두 로드되면 할일
	//대상 .addEventListener('이벤트종류',할일);
	//DOMcontentLoaded
	//할일 == 함수 == function(){실제로할일  }
document.addEventListener('DOMContentLoaded',function(){
	//변수 지정
	
	var slideWrap = document.querySelector('.container'), //컨테이너 DIV
	slideContainer = document.querySelector('.slider-container'),  //UL 
	slide = document.querySelectorAll('.slide'), //각각 li
	navPrev = document.getElementById('prev'), //이전버튼
	navNext = document.getElementById('next'), //이후버튼
	slideHeight = 0, //슬라이드(li) 높이 
	currentIndex = 0, //현재 슬라이드 인덱스
	slideCount=slide.length;
	slideWrap.style.height = '200px';
	//슬라이드의 높이 확인하여 부모의 높이로 지정하기 -대상.offsetHeight 
	for( var i=0 ; i < slideCount; i++ ){
		if(slideHeight < slide[i].offsetHeight){
			slideHeight = slide[i].offsetHeight;
		}
	}	
	console.log(slideHeight);
		
	slideWrap.style.height = slideHeight + 'px';
	slideContainer.style.height = slideHeight + 'px'; 
		
	//슬라이드가 있으면 가로로 배열하기
	for(var a = 0; a < slideCount; a++){
		slide[a].style.left =a * 100 + 'px';
	}

	//슬라이드 이동함수
	function goToSlide(idx){
		slideContainer.style.left = -100 * idx + 'px';
		slideContainer.classList.add('animated');
		currentIndex = idx; 
	}
	
	//버튼기능 업데이트 함수
	//버튼을 클릭하면 슬라이드 이동시키기
	
	//다음버튼을 클릭하면 할일, 이전 버튼을 클릭하면 할일.
	navPrev.addEventListener('click',function(){
		//goToSlide(currentIndex - 1);//슬라이드 이동함수
		
		if(currentIndex== 0 ){
			//navPrev.classList.add('disabled');
			goToSlide(slideCount -1);
		}else{
			//navPrev.classList.remove('disabled');
			goToSlide(currentIndex -1);
		}
	});
	navNext.addEventListener('click',function(){
		//goToSlide(currentIndex + 1);
		//마지막이라면 navNext가 안보이도록, 아니라면 다시보이도록
		if(currentIndex==slideCount -1){//마지막
			//navNext.classList.add('disabled');
			goToSlide(0);
		}else{
			//navNext.classList.remove('disabled');
			goToSlide(currentIndex + 1);
		}
	});
	//첫번쨰 슬라이드 먼저 보이게 하기
});
