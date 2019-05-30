package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.dto.KeyValueDTO;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.KeyValue;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.DocumentRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.EventAuditRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.KeyValueRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.NotificationRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.PackageRepository;
import com.opencsv.CSVWriter;

@Component
public class ExportServiceImpl implements ExportService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EventAuditRepository eventAuditRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    KeyValueRepository keyValueRepository;

    @Override
    public String exportEventAuditTable() {
        try {
            logger.info("Exporting Event Auit Table");
            File file = new File("C:\\export_data\\EVENT_AUDIT_TABLE.csv");
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile, '|', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            List<String[]> data = new ArrayList<String[]>();

            List<EventAudit> eventAudits = eventAuditRepository.findAll();
            logger.info("Number of Records Exported: {}", eventAudits.size());

            for (EventAudit e : eventAudits) {
                data.add(new String[] { e.getEventAuditKey(), e.getNotificationEventFromStatus(),
                        e.getNotificationEventToStatus(), e.getPackageEventFromStatus(), e.getPackageEventToStatus(),
                        e.getLastModifiedDate().toString(), e.getNotificationKey(), e.getPackageKey() });
            }
            writer.writeAll(data);
            writer.close();
        } catch (Exception e) {
            logger.error("Error while exporting EVENT_AUDIT_TBL {}", e);
        }
        return null;
    }

    @Override
    public String exportKeyValueTable() {
        try {
            logger.info("Exporting Key Value Table");

            File file = new File("C:\\export_data\\KEY_VALUE_TABLE.csv");
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile, '|', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            List<String[]> data = new ArrayList<String[]>();

            List<KeyValue> keyValues = keyValueRepository.findAll();
            logger.info("Number of Records Exported: {}", keyValues.size());

            keyValues.forEach(keyValue -> {
                KeyValueDTO keyValueDTO = new KeyValueDTO();
                BeanUtils.copyProperties(keyValue, keyValueDTO);
                System.out.println(keyValue.getKeyValuePairId().toString());
                if (keyValue.getKeyValuePairTypeCode().equals("NOTIFICATION_TBL")) {
                    keyValueDTO.setForiegnKeyId(
                            notificationRepository.findNotificationIdByKeyValuePairId(keyValue.getKeyValuePairId()));
                } else if (keyValue.getKeyValuePairTypeCode().equals("PACKAGE_TBL")) {
                    keyValueDTO.setForiegnKeyId(
                            packageRepository.findPackageIdByKeyValuePairId(keyValue.getKeyValuePairId()));
                } else { // (keyValue.getKeyValuePairTypeCode().equals("DOCUMENT_TBL")) {
                    keyValueDTO.setForiegnKeyId(
                            documentRepository.findDocumentIdByKeyValuePairId(keyValue.getKeyValuePairId()));
                }

                data.add(new String[] { keyValueDTO.getKeyValueKey(), keyValueDTO.getKey(), keyValueDTO.getValue(),
                        keyValueDTO.getLastModifiedDate().toString(), keyValueDTO.getKeyValuePairId().toString(),
                        keyValueDTO.getKeyValuePairTypeCode(), keyValueDTO.getForiegnKeyId() });

            });
            writer.writeAll(data);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
