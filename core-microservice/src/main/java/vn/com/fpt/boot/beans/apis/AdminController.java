package vn.com.fpt.boot.beans.apis;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.com.fpt.boot.beans.Entities.*;
import vn.com.fpt.boot.beans.Service.*;
import vn.com.fpt.boot.beans.forms.UserForm;
import vn.com.fpt.boot.beans.models.TokenResponse;


@RestController
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private LandService landService;
	@Autowired
	private BlockService blockService;
	@Autowired
	private PlantService plantService;
	@Autowired
	private VegetableService vegetableService;
	@PostMapping("login")
	public @ResponseBody TokenResponse doLogin(@RequestBody UserForm form){
		TokenResponse tokenResponse= new TokenResponse();
		System.out.println(form.getUserName());
		if (StringUtils.isNotBlank(form.getUserName())){
				Users user= userService.getUserByUserName(form.getUserName());
				if (user!=null){
				if (user.getPassWord().equals(form.getPassword())){													
					tokenResponse.setStatusCode(200);
					tokenResponse.setSuccess(true);
					tokenResponse.setToken(form.getUserName());	
					tokenResponse.setFullName(user.getFullName());
					return tokenResponse;
							
				}
				else {
					tokenResponse.setStatusCode(400);
					tokenResponse.setSuccess(false);
					tokenResponse.setErrorMessage("Wrong UserName or PassWord");
					return tokenResponse;
				}
				
		}
				else {
					tokenResponse.setStatusCode(600);
					tokenResponse.setSuccess(false);
					tokenResponse.setErrorMessage("Users not found");
					return tokenResponse;
				}
		
		
		}
		tokenResponse.setStatusCode(500);
		tokenResponse.setSuccess(false);
		tokenResponse.setErrorMessage("UserName or Password cannot be empty");
		return tokenResponse;
		
	
	}
	@GetMapping("lands")
	public @ResponseBody List<Land> getLands(HttpServletRequest request){
		String token= request.getHeader("Authorization");
		TokenResponse response= new TokenResponse();
		List<Land> lands= landService.getAll();
		return lands;
	}
	@GetMapping("blocks")
	public @ResponseBody List<Block> getBlock(HttpServletRequest request, @RequestParam("landid") long landId){
		String token= request.getHeader("Authorization");
		TokenResponse response= new TokenResponse();
		System.out.print("token: "+ token+ " landId: "+ landId);
		List<Block> blocks= blockService.findBlockByLandId(token,landId);
		return blocks;
	}
	@GetMapping("block/{blockId}")
	public @ResponseBody Block getBlockById(HttpServletRequest request, @PathVariable("blockId") long blockId){
		String token= request.getHeader("Authorization");
		TokenResponse response= new TokenResponse();
		Block block= blockService.findBlockByBlockId(token, blockId);
		return block;
	}
	@GetMapping("land/{landId}")
	public @ResponseBody Land getLandById(HttpServletRequest request, @PathVariable("landId") long blockId){
		String token= request.getHeader("Authorization");
		TokenResponse response= new TokenResponse();
		Land land= landService.findById(blockId);
		return land;
	}
	@GetMapping("plants")
	public @ResponseBody List<Plant> getPlants(HttpServletRequest request){
		String token= request.getHeader("Authorization");
		TokenResponse response= new TokenResponse();
		List<Plant> plants= plantService.getAll();
		return plants;
	}

	@GetMapping("plant/{plantId}")
	public @ResponseBody Plant getPlantById(HttpServletRequest request, @PathVariable("plantId") long blockId){
		String token= request.getHeader("Authorization");
		TokenResponse response= new TokenResponse();
		Plant plant= plantService.findById(blockId);
		return plant;
	}
	@GetMapping("vegetable")
	public @ResponseBody List<Vegetable> getVegetable(HttpServletRequest request){
		String token= request.getHeader("Authorization");
		TokenResponse response= new TokenResponse();
		List<Vegetable> vegetables= vegetableService.getAll();
		return vegetables;
	}

}
