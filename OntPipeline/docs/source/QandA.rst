FAQ
===
What bioinformatics tools are used?
___________________________________
* Guppy v3.0.3 https://community.nanoporetech.com
* Porechop 0.2.3 https://github.com/rrwick/Porechop
* NanoStat 1.1.2 https://github.com/wdecoster/nanostat
* NanoFilt 2.2.0 https://github.com/wdecoster/nanofilt
* Unicycler v0.4.8-beta https://github.com/rrwick/Unicycler
* BUSCO 3.0.2 https://busco.ezlab.org

Where can I find output files?
_______________________________
- :file:`\\Analysis\\Basecalled` (reads after basecalling)
- :file:`\\Analysis\\Barcodes` (reads after demultiplexing)
- :file:`\\Analysis\\AdapterTrimmedFiles` (reads after adapter trimming)
- :file:`\\Analysis\\FiltedFiles` (reads after filtering)
- :file:`\\Analysis\\StatFiles` (statitstical data before and after filtering)
- :file:`\\Analysis\\Logs` (log files)
- :file:`\\Analysis\\Assembly` (reads after assembly)
- :file:`\\Analysis\\Polishing` (polishing data)
- :file:`\\Analysis\\Assembly\\Busco` (busco check result)