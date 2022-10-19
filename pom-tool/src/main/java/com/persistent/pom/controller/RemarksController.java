package com.persistent.pom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.customexception.EntityAlreadyExistException;
import com.persistent.pom.customexception.NoSuchIDException;
import com.persistent.pom.entities.Remark;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.RemarkService;

@RestController
public class RemarksController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	RemarkService remarkService;

	@GetMapping(value = "/remarks")
	public ResponseEntity<ResponseMessage<List<Remark>>> getRemarks() throws Exception {
		LOGGER.info("Entered Remarks controller to get remarks list :: method = getRemarks");

		List<Remark> remarks = remarkService.getRemarks();
		ResponseMessage<List<Remark>> responseMessage = new ResponseMessage<>();
		if (remarks.size() == 0) {
			responseMessage.setStatusCode(HttpStatus.OK);
			responseMessage.setLength(0);
			responseMessage.setData(new ArrayList<>());
			responseMessage.setMessage("No remarks found");
			LOGGER.info("Exit Remarks controller with no remarks found :: method = getRemarks");
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);

		}
		responseMessage.setStatusCode(HttpStatus.OK);
		responseMessage.setLength(remarks.size());
		responseMessage.setMessage("List of remarks that are found");
		responseMessage.setData(remarks);
		LOGGER.info("Exit Remarks controller by fetching list of remarks :: method = getRemarks");
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	@GetMapping(value = "/remark")
	public ResponseEntity<ResponseMessage<Remark>> getParticularRemark(@RequestParam("id") int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Remarks controller to find remark with its id:: method = getRemark");
		Optional<Remark> optRemark = remarkService.getRemark(id);
		if (!optRemark.isPresent()) {
			LOGGER.info("Exit Remarks controller with no such id found :: method = getRemark");
			throw new NoSuchIDException();
		}
		Remark remark = optRemark.get();
		ResponseMessage<Remark> response = new ResponseMessage<>();
		response.setData(remark);
		response.setLength(1);
		response.setMessage("Details of the remark with id : " + id);
		LOGGER.info("Exit Remarks controller finding remark with given id :: method = getRemark");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/remark")
	public ResponseEntity<ResponseMessage<Remark>> addRemark(@RequestBody Remark rmrk)
			throws EntityAlreadyExistException, Exception {
		LOGGER.info("Entered Remarks controller to add an remark:: method = addRemark");
		Optional<Remark> optRemark = remarkService.getRemark(rmrk.getId());
		if (optRemark.isPresent()) {
			LOGGER.info("Exit Remarks controller as same remark exists :: method = addRemark ");
			throw new EntityAlreadyExistException();
		}
		Remark remark = remarkService.addRemark(rmrk);
		ResponseMessage<Remark> response = new ResponseMessage<>();
		response.setData(remark);
		response.setMessage("Remarks data");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Remarks controller after adding remark :: method = addRemark ");
		return new ResponseEntity<ResponseMessage<Remark>>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/remark")
	public ResponseEntity<ResponseMessage<Remark>> updateRemark(@RequestBody Remark tsk)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Remarks controller to update an remark :: method = updateRemark ");
		Optional<Remark> optRemark = remarkService.getRemark(tsk.getId());
		if (!optRemark.isPresent()) {
			LOGGER.error("Exit Remarks controller as no remark exists with that id:: method = updateRemark ");
			throw new NoSuchIDException();
		}
		Remark task = optRemark.get();
		ResponseMessage<Remark> response = new ResponseMessage<>();
		response.setData(task);
		response.setMessage("Data after updating the remark");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Remarks controller after updating remark :: method = updateRemark ");
		return new ResponseEntity<ResponseMessage<Remark>>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/remark")
	public ResponseEntity<ResponseMessage<Remark>> deleteRemark(@RequestParam int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Remarks controller to delete an remark :: method = deleteRemark");
		Optional<Remark> optRemark = remarkService.getRemark(id);
		if (!optRemark.isPresent()) {
			LOGGER.info("Exit Remarks controller as no remark exists with that id:: method = deleteRemark ");
			throw new NoSuchIDException();
		}
		remarkService.deleteRemark(id);
		ResponseMessage<Remark> response = new ResponseMessage<>();
		response.setData(optRemark.get());
		response.setMessage("Deletion sucessful");
		LOGGER.info("Exit Remarks controller after deleting remark sucessfully:: method = deleteRemark ");
		return new ResponseEntity<ResponseMessage<Remark>>(response, HttpStatus.OK);

	}

}
