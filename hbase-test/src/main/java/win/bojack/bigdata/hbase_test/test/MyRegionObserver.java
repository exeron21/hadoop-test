package win.bojack.bigdata.hbase_test.test;

import com.google.common.collect.ImmutableList;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.coprocessor.RegionObserver;
import org.apache.hadoop.hbase.filter.ByteArrayComparable;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.io.FSDataInputStreamWrapper;
import org.apache.hadoop.hbase.io.Reference;
import org.apache.hadoop.hbase.io.hfile.CacheConfig;
import org.apache.hadoop.hbase.regionserver.*;
import org.apache.hadoop.hbase.regionserver.compactions.CompactionRequest;
import org.apache.hadoop.hbase.regionserver.wal.HLogKey;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Pair;
import org.apache.hadoop.hbase.wal.WALKey;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NavigableSet;

public class MyRegionObserver implements RegionObserver {

    private void log (String str) {
        try {
            FileWriter fw = new FileWriter("/home/hdp/coprocessor.log", true);
            fw.write(str + "\r\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final MyBaseRegionObserver baseRegionObserver = new MyBaseRegionObserver();

    public void start(CoprocessorEnvironment e) throws IOException {
        baseRegionObserver.start(e);
        log("MyRegionObserver.start .... ");
    }

    public void stop(CoprocessorEnvironment e) throws IOException {
        baseRegionObserver.stop(e);
        log("MyRegionObserver.stop .... ");
    }

    public void preOpen(ObserverContext<RegionCoprocessorEnvironment> e) throws IOException {
        baseRegionObserver.preOpen(e);
        log("MyRegionObserver.preOpen .... ");
    }

    public void postOpen(ObserverContext<RegionCoprocessorEnvironment> e) {
        baseRegionObserver.postOpen(e);
        log("MyRegionObserver.postOpen .... ");
    }

    public void postLogReplay(ObserverContext<RegionCoprocessorEnvironment> e) {
        baseRegionObserver.postLogReplay(e);
    }

    public void preClose(ObserverContext<RegionCoprocessorEnvironment> c, boolean abortRequested) throws IOException {
        baseRegionObserver.preClose(c, abortRequested);
    }

    public void postClose(ObserverContext<RegionCoprocessorEnvironment> e, boolean abortRequested) {
        baseRegionObserver.postClose(e, abortRequested);
    }

    public InternalScanner preFlushScannerOpen(ObserverContext<RegionCoprocessorEnvironment> c, Store store, KeyValueScanner memstoreScanner, InternalScanner s) throws IOException {
        return baseRegionObserver.preFlushScannerOpen(c, store, memstoreScanner, s);
    }

    public void preFlush(ObserverContext<RegionCoprocessorEnvironment> e) throws IOException {
        baseRegionObserver.preFlush(e);
    }

    public void postFlush(ObserverContext<RegionCoprocessorEnvironment> e) throws IOException {
        baseRegionObserver.postFlush(e);
    }

    public InternalScanner preFlush(ObserverContext<RegionCoprocessorEnvironment> e, Store store, InternalScanner scanner) throws IOException {
        return baseRegionObserver.preFlush(e, store, scanner);
    }

    public void postFlush(ObserverContext<RegionCoprocessorEnvironment> e, Store store, StoreFile resultFile) throws IOException {
        baseRegionObserver.postFlush(e, store, resultFile);
    }

    public void preSplit(ObserverContext<RegionCoprocessorEnvironment> e) throws IOException {
        baseRegionObserver.preSplit(e);
    }

    public void preSplit(ObserverContext<RegionCoprocessorEnvironment> c, byte[] splitRow) throws IOException {
        baseRegionObserver.preSplit(c, splitRow);
    }

    public void preSplitBeforePONR(ObserverContext<RegionCoprocessorEnvironment> ctx, byte[] splitKey, List<Mutation> metaEntries) throws IOException {
        baseRegionObserver.preSplitBeforePONR(ctx, splitKey, metaEntries);
    }

    public void preSplitAfterPONR(ObserverContext<RegionCoprocessorEnvironment> ctx) throws IOException {
        baseRegionObserver.preSplitAfterPONR(ctx);
    }

    public void preRollBackSplit(ObserverContext<RegionCoprocessorEnvironment> ctx) throws IOException {
        baseRegionObserver.preRollBackSplit(ctx);
    }

    public void postRollBackSplit(ObserverContext<RegionCoprocessorEnvironment> ctx) throws IOException {
        baseRegionObserver.postRollBackSplit(ctx);
    }

    public void postCompleteSplit(ObserverContext<RegionCoprocessorEnvironment> ctx) throws IOException {
        baseRegionObserver.postCompleteSplit(ctx);
    }

    public void postSplit(ObserverContext<RegionCoprocessorEnvironment> e, Region l, Region r) throws IOException {
        baseRegionObserver.postSplit(e, l, r);
    }

    public void preCompactSelection(ObserverContext<RegionCoprocessorEnvironment> c, Store store, List<StoreFile> candidates) throws IOException {
        baseRegionObserver.preCompactSelection(c, store, candidates);
    }

    public void preCompactSelection(ObserverContext<RegionCoprocessorEnvironment> c, Store store, List<StoreFile> candidates, CompactionRequest request) throws IOException {
        baseRegionObserver.preCompactSelection(c, store, candidates, request);
    }

    public void postCompactSelection(ObserverContext<RegionCoprocessorEnvironment> c, Store store, ImmutableList<StoreFile> selected) {
        baseRegionObserver.postCompactSelection(c, store, selected);
    }

    public void postCompactSelection(ObserverContext<RegionCoprocessorEnvironment> c, Store store, ImmutableList<StoreFile> selected, CompactionRequest request) {
        baseRegionObserver.postCompactSelection(c, store, selected, request);
    }

    public InternalScanner preCompact(ObserverContext<RegionCoprocessorEnvironment> e, Store store, InternalScanner scanner, ScanType scanType) throws IOException {
        return baseRegionObserver.preCompact(e, store, scanner, scanType);
    }

    public InternalScanner preCompact(ObserverContext<RegionCoprocessorEnvironment> e, Store store, InternalScanner scanner, ScanType scanType, CompactionRequest request) throws IOException {
        return baseRegionObserver.preCompact(e, store, scanner, scanType, request);
    }

    public InternalScanner preCompactScannerOpen(ObserverContext<RegionCoprocessorEnvironment> c, Store store, List<? extends KeyValueScanner> scanners, ScanType scanType, long earliestPutTs, InternalScanner s) throws IOException {
        return baseRegionObserver.preCompactScannerOpen(c, store, scanners, scanType, earliestPutTs, s);
    }

    public InternalScanner preCompactScannerOpen(ObserverContext<RegionCoprocessorEnvironment> c, Store store, List<? extends KeyValueScanner> scanners, ScanType scanType, long earliestPutTs, InternalScanner s, CompactionRequest request) throws IOException {
        return baseRegionObserver.preCompactScannerOpen(c, store, scanners, scanType, earliestPutTs, s, request);
    }

    public void postCompact(ObserverContext<RegionCoprocessorEnvironment> e, Store store, StoreFile resultFile) throws IOException {
        baseRegionObserver.postCompact(e, store, resultFile);
    }

    public void postCompact(ObserverContext<RegionCoprocessorEnvironment> e, Store store, StoreFile resultFile, CompactionRequest request) throws IOException {
        baseRegionObserver.postCompact(e, store, resultFile, request);
    }

    public void preGetClosestRowBefore(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row, byte[] family, Result result) throws IOException {
        baseRegionObserver.preGetClosestRowBefore(e, row, family, result);
    }

    public void postGetClosestRowBefore(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row, byte[] family, Result result) throws IOException {
        baseRegionObserver.postGetClosestRowBefore(e, row, family, result);
    }

    public void preGetOp(ObserverContext<RegionCoprocessorEnvironment> e, Get get, List<Cell> results) throws IOException {
        baseRegionObserver.preGetOp(e, get, results);
        String rowKey = Bytes.toString(get.getRow());
        log("MyRegionObserver.preGetOp() : rowKey = " + rowKey);
    }

    public void postGetOp(ObserverContext<RegionCoprocessorEnvironment> e, Get get, List<Cell> results) throws IOException {
        baseRegionObserver.postGetOp(e, get, results);
        String rowKey = Bytes.toString(get.getRow());
        log("MyRegionObserver.postGetOp() : rowKey = " + rowKey);
    }

    public boolean preExists(ObserverContext<RegionCoprocessorEnvironment> e, Get get, boolean exists) throws IOException {
        return baseRegionObserver.preExists(e, get, exists);
    }

    public boolean postExists(ObserverContext<RegionCoprocessorEnvironment> e, Get get, boolean exists) throws IOException {
        return baseRegionObserver.postExists(e, get, exists);
    }

    public void prePut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        baseRegionObserver.prePut(e, put, edit, durability);
        String rowKey = Bytes.toString(put.getRow());
        log("MyRegionObserver.prePut() = " + rowKey);
    }

    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        baseRegionObserver.postPut(e, put, edit, durability);
        String rowKey = Bytes.toString(put.getRow());
        log("MyRegionObserver.postPut() = " + rowKey);
    }

    public void preDelete(ObserverContext<RegionCoprocessorEnvironment> e, Delete delete, WALEdit edit, Durability durability) throws IOException {
        baseRegionObserver.preDelete(e, delete, edit, durability);
        String rowKey = Bytes.toString(delete.getRow());
        log("MyRegionObserver.preDelete() = " + rowKey);
    }

    public void prePrepareTimeStampForDeleteVersion(ObserverContext<RegionCoprocessorEnvironment> e, Mutation delete, Cell cell, byte[] byteNow, Get get) throws IOException {
        baseRegionObserver.prePrepareTimeStampForDeleteVersion(e, delete, cell, byteNow, get);
    }

    public void postDelete(ObserverContext<RegionCoprocessorEnvironment> e, Delete delete, WALEdit edit, Durability durability) throws IOException {
        baseRegionObserver.postDelete(e, delete, edit, durability);
        String rowKey = Bytes.toString(delete.getRow());
        log("MyRegionObserver.postDelete() = " + rowKey);
    }

    public void preBatchMutate(ObserverContext<RegionCoprocessorEnvironment> c, MiniBatchOperationInProgress<Mutation> miniBatchOp) throws IOException {
        baseRegionObserver.preBatchMutate(c, miniBatchOp);
    }

    public void postBatchMutate(ObserverContext<RegionCoprocessorEnvironment> c, MiniBatchOperationInProgress<Mutation> miniBatchOp) throws IOException {
        baseRegionObserver.postBatchMutate(c, miniBatchOp);
    }

    public void postBatchMutateIndispensably(ObserverContext<RegionCoprocessorEnvironment> ctx, MiniBatchOperationInProgress<Mutation> miniBatchOp, boolean success) throws IOException {
        baseRegionObserver.postBatchMutateIndispensably(ctx, miniBatchOp, success);
    }

    public boolean preCheckAndPut(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row, byte[] family, byte[] qualifier, CompareFilter.CompareOp compareOp, ByteArrayComparable comparator, Put put, boolean result) throws IOException {
        return baseRegionObserver.preCheckAndPut(e, row, family, qualifier, compareOp, comparator, put, result);
    }

    public boolean preCheckAndPutAfterRowLock(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row, byte[] family, byte[] qualifier, CompareFilter.CompareOp compareOp, ByteArrayComparable comparator, Put put, boolean result) throws IOException {
        return baseRegionObserver.preCheckAndPutAfterRowLock(e, row, family, qualifier, compareOp, comparator, put, result);
    }

    public boolean postCheckAndPut(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row, byte[] family, byte[] qualifier, CompareFilter.CompareOp compareOp, ByteArrayComparable comparator, Put put, boolean result) throws IOException {
        return baseRegionObserver.postCheckAndPut(e, row, family, qualifier, compareOp, comparator, put, result);
    }

    public boolean preCheckAndDelete(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row, byte[] family, byte[] qualifier, CompareFilter.CompareOp compareOp, ByteArrayComparable comparator, Delete delete, boolean result) throws IOException {
        return baseRegionObserver.preCheckAndDelete(e, row, family, qualifier, compareOp, comparator, delete, result);
    }

    public boolean preCheckAndDeleteAfterRowLock(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row, byte[] family, byte[] qualifier, CompareFilter.CompareOp compareOp, ByteArrayComparable comparator, Delete delete, boolean result) throws IOException {
        return baseRegionObserver.preCheckAndDeleteAfterRowLock(e, row, family, qualifier, compareOp, comparator, delete, result);
    }

    public boolean postCheckAndDelete(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row, byte[] family, byte[] qualifier, CompareFilter.CompareOp compareOp, ByteArrayComparable comparator, Delete delete, boolean result) throws IOException {
        return baseRegionObserver.postCheckAndDelete(e, row, family, qualifier, compareOp, comparator, delete, result);
    }

    public Result preAppend(ObserverContext<RegionCoprocessorEnvironment> e, Append append) throws IOException {
        return baseRegionObserver.preAppend(e, append);
    }

    public Result preAppendAfterRowLock(ObserverContext<RegionCoprocessorEnvironment> e, Append append) throws IOException {
        return baseRegionObserver.preAppendAfterRowLock(e, append);
    }

    public Result postAppend(ObserverContext<RegionCoprocessorEnvironment> e, Append append, Result result) throws IOException {
        return baseRegionObserver.postAppend(e, append, result);
    }

    public long preIncrementColumnValue(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row, byte[] family, byte[] qualifier, long amount, boolean writeToWAL) throws IOException {
        return baseRegionObserver.preIncrementColumnValue(e, row, family, qualifier, amount, writeToWAL);
    }

    public long postIncrementColumnValue(ObserverContext<RegionCoprocessorEnvironment> e, byte[] row, byte[] family, byte[] qualifier, long amount, boolean writeToWAL, long result) throws IOException {
        return baseRegionObserver.postIncrementColumnValue(e, row, family, qualifier, amount, writeToWAL, result);
    }

    public Result preIncrement(ObserverContext<RegionCoprocessorEnvironment> e, Increment increment) throws IOException {
        return baseRegionObserver.preIncrement(e, increment);
    }

    public Result preIncrementAfterRowLock(ObserverContext<RegionCoprocessorEnvironment> e, Increment increment) throws IOException {
        return baseRegionObserver.preIncrementAfterRowLock(e, increment);
    }

    public Result postIncrement(ObserverContext<RegionCoprocessorEnvironment> e, Increment increment, Result result) throws IOException {
        return baseRegionObserver.postIncrement(e, increment, result);
    }

    public RegionScanner preScannerOpen(ObserverContext<RegionCoprocessorEnvironment> e, Scan scan, RegionScanner s) throws IOException {
        return baseRegionObserver.preScannerOpen(e, scan, s);
    }

    public KeyValueScanner preStoreScannerOpen(ObserverContext<RegionCoprocessorEnvironment> c, Store store, Scan scan, NavigableSet<byte[]> targetCols, KeyValueScanner s) throws IOException {
        return baseRegionObserver.preStoreScannerOpen(c, store, scan, targetCols, s);
    }

    public RegionScanner postScannerOpen(ObserverContext<RegionCoprocessorEnvironment> e, Scan scan, RegionScanner s) throws IOException {
        return baseRegionObserver.postScannerOpen(e, scan, s);
    }

    public boolean preScannerNext(ObserverContext<RegionCoprocessorEnvironment> e, InternalScanner s, List<Result> results, int limit, boolean hasMore) throws IOException {
        return baseRegionObserver.preScannerNext(e, s, results, limit, hasMore);
    }

    public boolean postScannerNext(ObserverContext<RegionCoprocessorEnvironment> e, InternalScanner s, List<Result> results, int limit, boolean hasMore) throws IOException {
        return baseRegionObserver.postScannerNext(e, s, results, limit, hasMore);
    }

    public boolean postScannerFilterRow(ObserverContext<RegionCoprocessorEnvironment> e, InternalScanner s, byte[] currentRow, int offset, short length, boolean hasMore) throws IOException {
        return baseRegionObserver.postScannerFilterRow(e, s, currentRow, offset, length, hasMore);
    }

    public void preScannerClose(ObserverContext<RegionCoprocessorEnvironment> e, InternalScanner s) throws IOException {
        baseRegionObserver.preScannerClose(e, s);
    }

    public void postScannerClose(ObserverContext<RegionCoprocessorEnvironment> e, InternalScanner s) throws IOException {
        baseRegionObserver.postScannerClose(e, s);
    }

    public void preWALRestore(ObserverContext<? extends RegionCoprocessorEnvironment> env, HRegionInfo info, WALKey logKey, WALEdit logEdit) throws IOException {
        baseRegionObserver.preWALRestore(env, info, logKey, logEdit);
    }

    public void preWALRestore(ObserverContext<RegionCoprocessorEnvironment> env, HRegionInfo info, HLogKey logKey, WALEdit logEdit) throws IOException {
        baseRegionObserver.preWALRestore(env, info, logKey, logEdit);
    }

    public void postWALRestore(ObserverContext<? extends RegionCoprocessorEnvironment> env, HRegionInfo info, WALKey logKey, WALEdit logEdit) throws IOException {
        baseRegionObserver.postWALRestore(env, info, logKey, logEdit);
    }

    public void postWALRestore(ObserverContext<RegionCoprocessorEnvironment> env, HRegionInfo info, HLogKey logKey, WALEdit logEdit) throws IOException {
        baseRegionObserver.postWALRestore(env, info, logKey, logEdit);
    }

    public void preBulkLoadHFile(ObserverContext<RegionCoprocessorEnvironment> ctx, List<Pair<byte[], String>> familyPaths) throws IOException {
        baseRegionObserver.preBulkLoadHFile(ctx, familyPaths);
    }

    public boolean postBulkLoadHFile(ObserverContext<RegionCoprocessorEnvironment> ctx, List<Pair<byte[], String>> familyPaths, boolean hasLoaded) throws IOException {
        return baseRegionObserver.postBulkLoadHFile(ctx, familyPaths, hasLoaded);
    }

    public StoreFile.Reader preStoreFileReaderOpen(ObserverContext<RegionCoprocessorEnvironment> ctx, FileSystem fs, Path p, FSDataInputStreamWrapper in, long size, CacheConfig cacheConf, Reference r, StoreFile.Reader reader) throws IOException {
        return baseRegionObserver.preStoreFileReaderOpen(ctx, fs, p, in, size, cacheConf, r, reader);
    }

    public StoreFile.Reader postStoreFileReaderOpen(ObserverContext<RegionCoprocessorEnvironment> ctx, FileSystem fs, Path p, FSDataInputStreamWrapper in, long size, CacheConfig cacheConf, Reference r, StoreFile.Reader reader) throws IOException {
        return baseRegionObserver.postStoreFileReaderOpen(ctx, fs, p, in, size, cacheConf, r, reader);
    }

    public Cell postMutationBeforeWAL(ObserverContext<RegionCoprocessorEnvironment> ctx, MutationType opType, Mutation mutation, Cell oldCell, Cell newCell) throws IOException {
        return baseRegionObserver.postMutationBeforeWAL(ctx, opType, mutation, oldCell, newCell);
    }

    public void postStartRegionOperation(ObserverContext<RegionCoprocessorEnvironment> ctx, Region.Operation op) throws IOException {
        baseRegionObserver.postStartRegionOperation(ctx, op);
    }

    public void postCloseRegionOperation(ObserverContext<RegionCoprocessorEnvironment> ctx, Region.Operation op) throws IOException {
        baseRegionObserver.postCloseRegionOperation(ctx, op);
    }

    public DeleteTracker postInstantiateDeleteTracker(ObserverContext<RegionCoprocessorEnvironment> ctx, DeleteTracker delTracker) throws IOException {
        return baseRegionObserver.postInstantiateDeleteTracker(ctx, delTracker);
    }

    private class MyBaseRegionObserver extends BaseRegionObserver {
    }
}
