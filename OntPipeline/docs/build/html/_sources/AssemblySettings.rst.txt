Assembly Settings
=================
Mode (Required)
_______________
Choose an assembly mode. 

* Conservative: Conservative mode is least likely to produce a complete assembly but has a very low risk of misassembly.
* Normal: Normal mode is intermediate regarding both completeness and misassembly risk. (Default)
* Bold: Bold mode is most likely to produce a complete assembly but carries greater risk of misassembly. 

Method (Required)
_________________
Choose an assembly method.

* Long-read-only assembly: Long-read-only assembly using only long reads.
* Hybrid assembly: Hybrid assembly using both Illumina read and long reads. (Default)

VCF (Optional)
______________
Produce a VCF by mapping the short reads to the final assembly if selected.

.. note::
  * Default: not selected.