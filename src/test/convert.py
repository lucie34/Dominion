#!/usr/bin/env python3
import re
import sys

start_pattern = '''\
{0}System.out.print("{1} : ");
{0}this.reset();
{0}try {{
'''

check_pattern = '''\
{0}this.check({1});
'''

end_pattern = '''\
{0}	if (this.isPassed()) {{ System.out.println("[OK]"); this.pass(); }}
{0}	else {{ System.out.println("[ÉCHEC]"); this.fail(); }}
{0}}} catch (Exception e) {{
{0}	System.out.println("[ERREUR]");
{0}	this.error();
{0}}}
'''

if __name__ == '__main__':
	for input_file in sys.argv[1:]:
		if input_file.endswith(".jap"):
			output_file = re.sub(r"\.jap$", r".java", input_file)
		else:
			continue
		print("{} -> {}".format(input_file, output_file))
		with open(input_file, 'r') as f_in,\
			 open(output_file, "w") as f_out:
			for l in f_in:
				m = re.search('^(\s*)@start "(.*)"', l)
				if m:
					l = start_pattern.format(m.group(1), m.group(2))
				m = re.search('^(\s*)@check (.*);', l)
				if m:
					l = check_pattern.format(m.group(1), m.group(2))
				m = re.search('^(\s*)}\s*@end;', l)
				if m:
					l = end_pattern.format(m.group(1))
				f_out.write(l)
