/*
 * Copyright 2025 <Your Organization or Name>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.cdap.directives.aggregate;

import io.cdap.wrangler.api.Directive;
import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.context.ExecutorContext;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import java.util.List;

public class AggregateStats implements Directive {
  private String sizeColumn;
  private String timeColumn;
  private String resultSizeColumn;
  private String resultTimeColumn;

  private long totalBytes = 0;
  private long totalMilliseconds = 0;

  @Override
  public void initialize(ExecutorContext context) {
    // Initialize the columns
    sizeColumn = context.getArguments().get(0);
    timeColumn = context.getArguments().get(1);
    resultSizeColumn = context.getArguments().get(2);
    resultTimeColumn = context.getArguments().get(3);
  }

  @Override
  public void execute(List<Row> rows, ExecutorContext context) {
    for (Row row : rows) {
      // Retrieve the byte size and time duration for the current row
      String sizeValue = row.getValue(sizeColumn).toString();
      String timeValue = row.getValue(timeColumn).toString();

      // Parse the byte size and time duration
      ByteSize byteSize = new ByteSize(sizeValue);
      TimeDuration timeDuration = new TimeDuration(timeValue);

      // Add to totals
      totalBytes += byteSize.getBytes();
      totalMilliseconds += timeDuration.getMilliseconds();
    }
  }

  @Override
  public void finalize(ExecutorContext context) {
    // Calculate the aggregates
    double totalMB = totalBytes / (1024.0 * 1024);
    double totalSec = totalMilliseconds / 1000.0;

    // Create a new row with the results
    Row resultRow = new Row();
    resultRow.add(resultSizeColumn, totalMB);
    resultRow.add(resultTimeColumn, totalSec);

    // Send the result row to the context
    context.getResults().add(resultRow);
  }
}
