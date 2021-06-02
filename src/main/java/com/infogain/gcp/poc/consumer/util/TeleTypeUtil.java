package com.infogain.gcp.poc.consumer.util;

import com.google.cloud.Timestamp;
import com.infogain.gcp.poc.consumer.dto.TeletypeEventDTO;
import com.infogain.gcp.poc.consumer.entity.TeleTypeEntity;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Slf4j
public class TeleTypeUtil {

    public static TeletypeEventDTO unmarshall(String message) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(TeletypeEventDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        TeletypeEventDTO teletypeEventDTO = (TeletypeEventDTO) unmarshaller.unmarshal(new StringReader(message));

        log.info("Teletypedto generated : {}", teletypeEventDTO);

        return teletypeEventDTO;
    }

    public static TeleTypeEntity convert(TeletypeEventDTO teletypeEventDTO, String message) {
        return TeleTypeEntity.builder()
                .hostLocator(teletypeEventDTO.getHostRecordLocator())
                .carrierCode(teletypeEventDTO.getCarrierCode())
                .createdTimestamp(Timestamp.now())
                .payload(message)
                .build();
    }
}