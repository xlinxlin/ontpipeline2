General Settings
================
ONT Dir. (Required)
___________________
Set the path to the Nanopore reads directory. 

.. note::
  * **Example:** :file:`/path/to/your/ONT/reads/directory`
  
Illumina Dir. (Optional)
________________________
Set the path to the Illumina reads directory. 

.. note::
  * **Example:** :file:`/path/to/your/Illumina/reads/directory`
  * Required if "hybrid-assembly" or/and "polishing" is used.

Output Dir. (Required)
______________________
Set the path to the output directory. 

.. note::
  * **Example:** :file:`/path/to/your/output/directory`
  * Required if "hybrid-assembly" or/and "polishing" is used.

Sample sheet. (Optional)
________________________
Set the path to the sample sheet file. 

.. note::
  * The sample sheet must be in .csv or .tsv format.
  * The sample name does not allowed contain "_".
  
Prefix. (Optional)
__________________
Set the prefix for the Nanopore reads after demultiplexing. 

.. note::
  * **Example:** ID .
  * Default value: barcode .

Threads (Required)
_____________________
Set the number of threads to run the analysis.

.. note::
  * Default value: 8.

Barcodes (Optional)
______________________
Set which barcodes should be included into the analysis. Put in the numbers and separate them with a comma.

.. note::
  * **Example:** 1,2,3,4
  * If you want to include all barcodes in your analysis, leave it blank.