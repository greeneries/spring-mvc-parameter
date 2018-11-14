# spring-mvc-parameter


### spring parameter test

##### 1. 
```
	@RequestMapping(value = {"/example1", "/ex1"}, 
			method = RequestMethod.GET, 
			produces = { "text/plain" },
			consumes = { "text/plain", "application/*", "text/html" }) 
	@ResponseBody  
	public String example1() {
		return "example1";
	}
```

```
	@GetMapping("/example2")
	public String example2(
			HttpServletRequest req, Model model,
			HttpSession session, Locale locale) {
		req.setAttribute("value1", 100);
		model.addAttribute("value2", 200);
		System.out.println(session == req.getSession());
		System.out.println(locale.getCountry() + "," + locale.getLanguage());
		return "example2";
	}
```

```
	@PostMapping("/example3")
	public ModelAndView example3(
			Map<String, String> map, User user) {
		System.out.println(map);
		System.out.println(user);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("example3");
		mav.addObject("map", map);
		mav.addObject("user", user);
		
		return mav;
	}
```

```
	@GetMapping("/example4") 
	@ResponseBody
	public String example4(
			HttpServletRequest req,
			@RequestParam(required=true) String id,
			@RequestParam(name="a", required=false, defaultValue="100") int age) {
		
		System.out.println(id);
		System.out.println(age);
		
		return req.getRequestURI();
	}
 ```
 
 
 ```
 	@GetMapping("/example5/{id}/devices/{no}")
	@ResponseBody
	public String example5(
			@PathVariable("id") String id,
			@PathVariable int no) {
		
		System.out.println(id);
		System.out.println(no);
		
		return "id = "+id + ", no = " + no;
	}
 ```
  
	
