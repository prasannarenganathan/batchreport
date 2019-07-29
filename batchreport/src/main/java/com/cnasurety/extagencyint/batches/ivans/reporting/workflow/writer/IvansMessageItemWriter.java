package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessage;
@Service
public class IvansMessageItemWriter implements ItemWriter<IvansMessage> {

	@Autowired
	WriteProcessor writeProcessor;
	
	  private static final String IVANS_MESSAGE_TABLE = "IVANSMESSAGETABLE_";
	
	@Override
	public void write(List<? extends IvansMessage> ivansMessages) throws Exception {
		 List<String[]> data = new ArrayList<String[]>();
 		 
 		for (IvansMessage e : ivansMessages) {
            data.add(e.getEntityDataString());
        }
 		writeProcessor.writeFile(data, IVANS_MESSAGE_TABLE);
 		
		
	}

}
