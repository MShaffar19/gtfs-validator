package org.mobilitydata.gtfsvalidator.db;

import org.junit.jupiter.api.Test;
import org.mobilitydata.gtfsvalidator.domain.entity.RawFileInfo;

import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryGtfsSpecRepositoryTest {

    private static final String TEST_ASCII_GTFS_FILE = "test_gtfs_spec.asciipb";
    private static final String REQUIRED_FILE_0 = "requiredFile0.txt";
    private static final String REQUIRED_FILE_1 = "requiredFile1.txt";
    private static final String OPTIONAL_FILE_0 = "optionalFile0.txt";
    private static final String OPTIONAL_FILE_1 = "optionalFile1.txt";
    private static final String REQUIRED_HEADER0 = "requiredHeader0";
    private static final String REQUIRED_HEADER_1 = "requiredHeader1";
    private static final String OPTIONAL_HEADER_0 = "optionalHeader0";
    private static final String OPTIONAL_HEADER_1 = "optionalHeader1";

    @Test
    void fileMarkedRequiredInSpecShouldBeListed() throws IOException {

        InMemoryGtfsSpecRepository underTest = new InMemoryGtfsSpecRepository(TEST_ASCII_GTFS_FILE);

        final Collection<String> requiredFilenameList = underTest.getRequiredFilenameList();

        assertEquals(2, requiredFilenameList.size());
        assert (requiredFilenameList.contains(REQUIRED_FILE_0));
        assert (requiredFilenameList.contains(REQUIRED_FILE_1));
    }

    @Test
    void fileMarkedOptionalInSpecShouldBeListed() throws IOException {

        InMemoryGtfsSpecRepository underTest = new InMemoryGtfsSpecRepository(TEST_ASCII_GTFS_FILE);

        final Collection<String> optionalFilenameList = underTest.getOptionalFilenameList();

        assertEquals(2, optionalFilenameList.size());

        assert (optionalFilenameList.contains(OPTIONAL_FILE_0));
        assert (optionalFilenameList.contains(OPTIONAL_FILE_1));

    }


    @Test
    void headerMarkedRequiredInRequiredFileShouldBeListed() throws IOException {

        InMemoryGtfsSpecRepository underTest = new InMemoryGtfsSpecRepository(TEST_ASCII_GTFS_FILE);

        final Collection<String> requiredHeaderListForRequiredFile0 = underTest.getRequiredHeadersForFile(
                RawFileInfo.builder().filename(REQUIRED_FILE_0).build());

        final Collection<String> requiredHeaderListForRequiredFile1 = underTest.getRequiredHeadersForFile(
                RawFileInfo.builder().filename(REQUIRED_FILE_1).build());


        assertEquals(1, requiredHeaderListForRequiredFile0.size());
        assert (requiredHeaderListForRequiredFile0.contains(REQUIRED_HEADER0));

        assertEquals(2, requiredHeaderListForRequiredFile1.size());
        assert (requiredHeaderListForRequiredFile1.contains(REQUIRED_HEADER0));
        assert (requiredHeaderListForRequiredFile1.contains(REQUIRED_HEADER_1));
    }

    @Test
    void headerMarkedRequiredInOptionalFileShouldBeListed() throws IOException {

        InMemoryGtfsSpecRepository underTest = new InMemoryGtfsSpecRepository(TEST_ASCII_GTFS_FILE);


        final Collection<String> requiredHeaderListForOptionalFile0 = underTest.getRequiredHeadersForFile(
                RawFileInfo.builder().filename(OPTIONAL_FILE_0).build());

        final Collection<String> requiredHeaderListForOptionalFile1 = underTest.getRequiredHeadersForFile(
                RawFileInfo.builder().filename(OPTIONAL_FILE_1).build());

        assertEquals(2, requiredHeaderListForOptionalFile0.size());
        assert (requiredHeaderListForOptionalFile0.contains(REQUIRED_HEADER0));
        assert (requiredHeaderListForOptionalFile0.contains(REQUIRED_HEADER_1));

        assertEquals(1, requiredHeaderListForOptionalFile1.size());
        assert (requiredHeaderListForOptionalFile1.contains(REQUIRED_HEADER0));
    }


    @Test
    void headerMarkedOptionalInRequiredFileShouldBeListed() throws IOException {

        InMemoryGtfsSpecRepository underTest = new InMemoryGtfsSpecRepository(TEST_ASCII_GTFS_FILE);

        final Collection<String> optionalHeaderListForRequiredFile0 = underTest.getOptionalHeadersForFile(
                RawFileInfo.builder().filename(REQUIRED_FILE_0).build());

        final Collection<String> optionalHeaderListForRequiredFile1 = underTest.getOptionalHeadersForFile(
                RawFileInfo.builder().filename(REQUIRED_FILE_1).build());

        assertEquals(2, optionalHeaderListForRequiredFile0.size());
        assert (optionalHeaderListForRequiredFile0.contains(OPTIONAL_HEADER_0));
        assert (optionalHeaderListForRequiredFile0.contains(OPTIONAL_HEADER_1));

        assertEquals(0, optionalHeaderListForRequiredFile1.size());
    }


    @Test
    void headerMarkedOptionalInOptionalFileShouldBeListed() throws IOException {

        InMemoryGtfsSpecRepository underTest = new InMemoryGtfsSpecRepository(TEST_ASCII_GTFS_FILE);

        final Collection<String> optionalHeaderListForOptionalFile0 = underTest.getOptionalHeadersForFile(
                RawFileInfo.builder().filename(OPTIONAL_FILE_0).build());

        final Collection<String> optionalHeaderListForOptionalFile1 = underTest.getOptionalHeadersForFile(
                RawFileInfo.builder().filename(OPTIONAL_FILE_1).build());

        assertEquals(1, optionalHeaderListForOptionalFile0.size());
        assert (optionalHeaderListForOptionalFile0.contains(OPTIONAL_HEADER_0));


        assertEquals(2, optionalHeaderListForOptionalFile1.size());
        assert (optionalHeaderListForOptionalFile0.contains(OPTIONAL_HEADER_0));
        assert (optionalHeaderListForOptionalFile1.contains(OPTIONAL_HEADER_1));
    }

}